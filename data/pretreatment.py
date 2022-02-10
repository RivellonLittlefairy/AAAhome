import pymysql


def pretreatment():
    db = pymysql.connect(host="127.0.0.1", user="root", password="fs4txdya", database="game_price_info", charset="utf8", port=3306)
    cursor = db.cursor()
    cursor.execute("select count(*) from game_info_steam")
    count = cursor.fetchall()
    count = int(count[0][0])
    cursor.execute("select count(*) from detail")
    begin = cursor.fetchall()
    begin = int(begin[0][0])
    for index in range(begin+1, count):
        sql = "select detail_page from game_info_steam where id=" + str(index)
        cursor.execute(sql)
        url = cursor.fetchall()[0][0]
        sql = "insert into detail(id,url) values(%s,%s)"
        cursor.execute(sql, (index, url))
        db.commit()


pretreatment()
