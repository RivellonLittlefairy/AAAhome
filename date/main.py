import pymysql
import xml.dom.minidom
from bs4 import BeautifulSoup
import requests
import time
import datetime

def writeToDatabase(db, name, coverImage, price, releaseDate, detailsPage):
    print(name)
    cursor = db.cursor()
    # 去除商标符号（无法保存）
    name = name.replace('®', '')
    # 处理单引号保存
    name = name.replace('\'', '\'\'')
    sql = 'select price_now from exam01 where game_name=\'' + name + '\''
    cursor.execute(sql)
    prices = cursor.fetchall()
    if len(prices) == 0:
        # 假如没有这个商品的记录，则插入一条记录最后一个字段值是当前时间戳
        sql = 'INSERT INTO exam01 (game_name, coverimage_url, price_now,price_lowest,relase_date, detail_page,nearly_lowest_timestamp)VALUES('
        sql2 = '\'' + name + '\',' + '\'' + coverImage + '\',' + '\'' + price + '\',' + '\'' + price + '\',' + '\'' + releaseDate + '\',' + '\'' + detailsPage + '\',' + '\'' + str(
            time.time()) + '\');'
        try:
            cursor.execute(sql + sql2)
            db.commit()
        except:
            db.rollback()
    else:
        # 如果数据库中的当前价格大于商城现在的价格，则需要更新库中价格，并且可能现在是史低价格，则更新时间戳
        if int(price) < list(prices[0])[0]:
            try:
                sql = 'update exam01 set price_now=\'' + price + '\' where game_name=\'' + name + '\''
                cursor.execute(sql)
                sql = 'select price_lowest from exam01 where game_name=\'' + name + '\''
                cursor.execute(sql)
                lowestPrice = cursor.fetchall()
                # 如果当前价格是史低
                if list(lowestPrice[0])[0] >= int(price):
                    sql = 'update exam01 set price_lowest=\'' + price + '\' where game_name=\'' + name + '\''
                    cursor.execute(sql)
                    sql = 'update exam01 set nearly_lowest_timestamp=\'' + str(
                        time.time()) + '\' where game_name=\'' + name + '\''
                    cursor.execute(sql)
                db.commit()
            except:
                db.rollback()
        else:
            if int(price) > list(prices[0])[0]:
                try:
                    sql = 'update exam01 set price_now=\'' + price + '\' where game_name=\'' + name + '\''
                    cursor.execute(sql)
                    db.commit()
                except:
                    db.rollback()
            else:
                sql = 'update exam01 set nearly_lowest_timestamp=\'' + str(
                    time.time()) + '\' where game_name=\'' + name + '\''
                cursor.execute(sql)
                db.commit()

# 读取数据库连接配置文档，获取数据库连接
def getDBConnection():
    dom = xml.dom.minidom.parse('db_conf.xml')
    root = dom.documentElement.getElementsByTagName("environments")[0]
    environments = root.getElementsByTagName("environment")
    for environment in environments:
        if environment.attributes["where"].value == "steam":
            host = environment.getElementsByTagName("host")[0].childNodes[0].nodeValue
            port = environment.getElementsByTagName("port")[0].childNodes[0].nodeValue
            user = environment.getElementsByTagName("user")[0].childNodes[0].nodeValue
            password = environment.getElementsByTagName("password")[0].childNodes[0].nodeValue
            database = environment.getElementsByTagName("database")[0].childNodes[0].nodeValue
            charset = environment.getElementsByTagName("charset")[0].childNodes[0].nodeValue
    return pymysql.connect(host=host, user=user, password="", database=database, charset=charset, port=int(port))

def savaDataOnByOn(start):
    r = requests.get(
        'https://store.steampowered.com/search/results/?query&start=' + start + '&count=50&dynamic_data=&sort_by=_ASC&snr=1_7_7_gl',timeout=100)
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
        db = getDBConnection()
        writeToDatabase(db, name, coverImage, price, releaseDate, detailsPage)
        db.close()

for rank in range(300):
    savaDataOnByOn(str(rank * 50))
