import pymysql
import xml.dom.minidom
from bs4 import BeautifulSoup
import requests

from getDB import getDBConnection


def getData():
    db = getDBConnection()
    cursor = db.cursor()
    cursor.execute("select count(*) from detail")
    count = cursor.fetchall()[0][0]
    headers = {
        'Accept-Language': 'en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7',
        'Cookie': 'browserid=2495550696968602157; timezoneOffset=28800,0; _ga=GA1.2.1991780315.1635763484; Steam_Language=schinese; lastagecheckage=1-0-1987; steamCountry=CN%7Cf8d2aa7d8c0ee27f17ba1d08d0feaf45; sessionid=ee1f5190118388f870218881; _gid=GA1.2.1254031853.1643959302; recentapps=%7B%221158310%22%3A1644034169%2C%221446780%22%3A1644033764%2C%22534380%22%3A1644033526%2C%22730%22%3A1644033519%2C%221426210%22%3A1643959300%2C%221054490%22%3A1641301841%2C%22322330%22%3A1641301820%2C%221547890%22%3A1639719271%2C%22703080%22%3A1639719266%2C%22646570%22%3A1639718965%7D; app_impressions=1303182@1_5_9__405|281990@1_5_9__412|236850@1_5_9__412|859580@1_5_9__412|203770@1_5_9__412|42960@1_5_9__412|1158310@1_5_9__412|1296731@1_5_9__412|1303182@1_5_9__412|1303183@1_5_9__412|1303184@1_5_9__412|1303183@1_5_9__405|210894@1_5_9__316_5|329010@1_5_9__316_4|1303184@1_5_9__316_3|1303183@1_5_9__316_2|1303182@1_5_9__316_1|281990@1_5_9__300_5|1099410@1_5_9__300_4|1466860@1_5_9__300_3|261550@1_5_9__300_2|236850@1_5_9__300_1'
    }
    for index in range(1, count + 1):
        sql = "select url,success from detail where id=%s"
        cursor.execute(sql, index)

        mark = cursor.fetchall()
        if mark[0][1] == 1:
            print("success" + str(index))
            continue
        url = mark[0][0]
        print(url)
        r = requests.get(url, timeout=20, headers=headers)
        saveData(index, r.text)


def saveData(id, text):
    db = getDBConnection()
    cursor = db.cursor()
    soup = BeautifulSoup(text, 'lxml')
    # 能不能打开
    success = soup.find_all(class_='agegate_birthday_selector')
    if len(success) == 1:
        return
    success = 1
    # 游戏名字
    name = soup.find(class_='apphub_AppName')
    if name is None:
        return
    name = name.string.strip()
    # 游戏简介
    comments = soup.find_all(class_='game_description_snippet')[0]
    if comments is None:
        return
    comments = comments.string.strip()
    # 原价
    price = soup.find(class_='game_purchase_price price')
    if price is None:
        price="0"
    price=price.string.strip()
    if not price.isdigit():
        price = 0
    else:
        price = price.replace("¥", "")
        price = price.replace("$", "")
        price = int(float(price)) * 100
    price = str(price)
    # isDlc
    isDlc = 0
    find = soup.find_all(class_='game_area_bubble game_area_dlc_bubble')
    if len(find) == 0:
        isDlc = 1
    # 评价
    find = soup.find_all(class_='game_review_summary positive')
    appraise = ""
    if len(find) != 0:
        appraise = find[0].string.strip()
    # dlc的名字
    dlcs = ""
    find = soup.find_all(class_='game_area_dlc_name')
    for child in find:
        dlcs += "," + child.string.strip()
    # 封面
    HDCoverImg = soup.find(class_='game_header_image_full')['src']
    # 标签
    tag = ""
    find = soup.find_all(class_='app_tag')
    for i in find:
        tag += "," + i.string.strip()
    # 宣传图片
    HDImg = ""
    find = soup.find_all(class_='highlight_strip_item highlight_strip_screenshot')
    for i in find:
        res = i.contents[1]['src']
        res = res.replace("116x65", "1920x1080")
        HDImg += "," + res
    # 宣传视频
    HDMovie = ""
    find = soup.find_all(class_='movie_thumb')
    for i in find:
        res = i['src']
        res = res.replace(".184x123.jpg", "480.webm")
        HDMovie += "," + res

    # 存入数据库
    sql = "update detail set gameName=%s,success=%s,highPrice=%s,isDlc=%s,appraise=%s,comments=%s,DlcNames=%s,HDCoverImg=%s,tag=%s,HDImg=%s,HDMovie=%s where id=%s"
    cursor.execute(sql, (name, success, price, isDlc, appraise, comments, dlcs, HDCoverImg, tag, HDImg, HDMovie, id))
    print((name, success, price, isDlc, appraise, comments, dlcs, HDCoverImg, tag, HDImg, HDMovie, id))
    db.commit()


saveData(0, open('doc.txt'))
getData()
