import pymysql
from bs4 import BeautifulSoup
import requests
import re
import datetime

def saveData(start):
    r = requests.get(
        'https://store.steampowered.com/search/results/?query&start=' + start + '&count=50&dynamic_data=&sort_by=_ASC&snr=1_7_7_gl')
    soup = BeautifulSoup(r.text, 'lxml')
    # 获取名字
    name = soup.find_all(class_='title')
    for i in range(len(name)):
        name[i] = name[i].string
    # 获取封面图
    img = soup.find_all('img')
    for i in range(len(img)):
        img[i] = img[i].get('src')
    # 获取发布日期
    releaseDate = soup.find_all(class_='col search_released responsive_secondrow')
    for i in range(len(releaseDate)):
        releaseDate[i] = releaseDate[i].string
    # 获取详情页面
    detailsPage = re.findall('https://store.steampowered.com/app/.+?"', r.text)
    for i in range(len(detailsPage)):
        detailsPage[i] = detailsPage[i][0:len(detailsPage[i]) - 1]
    price = []
    # 包含了每个游戏的价格，折扣情况的母标签
    bigBox = soup.find_all(class_='responsive_search_name_combined')
    # col.search_price.discounted.responsive_secondrow
    box = []
    for i in bigBox:
        # 无折扣 col search_price_discount_combined responsive_secondrow
        # 有折扣col search_price_discount_combined responsive_secondrow
        price.append(i.find(class_='col search_price_discount_combined responsive_secondrow')['data-price-final'])
    count = len(name);
    if (len(img) < count or len(price) < count or len(releaseDate) < count or len(detailsPage) < count):
        print('error data' + str(int(start) / 50))
        return
    db = pymysql.connect(host="127.0.0.1", user="root", password="", database="exam", charset="utf8")
    for i in range(len(name)):
        writeToDatabase(db, name[i], img[i], price[i], releaseDate[i], detailsPage[i])
    db.close()


def writeToDatabase(db, name, coverImage, price, releaseDate, detailsPage):
    cursor = db.cursor()
    # 去除商标符号（无法保存）
    name = name.replace('®', '')
    # 处理单引号保存
    name = name.replace('\'', '\'\'')
    sql = 'select pricenow from game where gamename=\'' + name + '\''
    cursor.execute(sql)
    prices = cursor.fetchall()
    if len(prices) == 0:
        sql = 'INSERT INTO game (gamename, coverImage, pricenow,lowestprice,releaseDate, detailsPage)VALUES('
        sql2 = '\'' + name + '\',' + '\'' + coverImage + '\',' + '\'' + price + '\',' + '\'' + price + '\',' + '\'' + releaseDate + '\',' + '\'' + detailsPage + '\');'
        try:
            cursor.execute(sql + sql2)
            db.commit()
        except:
            db.rollback()
    else:
        if list(prices[0])[0] > int(price):
            try:
                sql = 'update game set pricenow=\''+price+'\' where gamename=\'' + name + '\''
                cursor.execute(sql)
                sql = 'select lowestprice from game where gamename=\'' + name + '\''
                cursor.execute(sql)
                lowestPrice=cursor.fetchall()
                if list(lowestPrice[0])[0] > int(price):
                    sql = 'update game set lowestprice=\''+price+'\' where gamename=\'' + name + '\''
                    cursor.execute(sql)
                db.commit()
            except:
                db.rollback()
        else:
            if list(prices[0])[0] < int(price):
                try:
                    sql = 'update game set pricenow=\''+price+'\' where gamename=\'' + name + '\''
                    cursor.execute(sql)
                    db.commit()
                except:
                    db.rollback()



def savaDataOnByOn(start):
    r = requests.get(
        'https://store.steampowered.com/search/results/?query&start=' + start + '&count=50&dynamic_data=&sort_by=_ASC&snr=1_7_7_gl')
    soup = BeautifulSoup(r.text, 'lxml')
    # search_result_row ds_collapse_flag
    lis = soup.find_all(class_='search_result_row ds_collapse_flag')
    for each in lis:
        detailsPage = each['href']
        soup = BeautifulSoup(str(each), 'lxml')
        releaseDate = soup.find(class_='col search_released responsive_secondrow').string
        # 如果有的游戏没有发行日期，加个默认值
        if releaseDate is None:
            releaseDate = 'empty'
        price = soup.find(class_='col search_price_discount_combined responsive_secondrow')['data-price-final']
        name = soup.find(class_='title').string
        coverImage = soup.find('img')['src']
        db = pymysql.connect(host="127.0.0.1", user="root", password="", database="exam", charset="utf8")
        writeToDatabase(db, name, coverImage, price, releaseDate, detailsPage)
        db.close()

print(datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
for rank in range(300):
    savaDataOnByOn(str(rank * 50))

