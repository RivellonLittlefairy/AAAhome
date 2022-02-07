from getDB import getDBConnection


def pretreatment():
    db = getDBConnection()
    cursor = db.cursor()
    cursor.execute("select count(*) from game_info_steam")
    count = cursor.fetchall()
    count = int(count[0][0])
    for index in range(1, count):
        sql = "select detail_page from game_info_steam where id=" + str(index)
        cursor.execute(sql)
        url = cursor.fetchall()[0][0]
        sql = "insert into detail(id,url) values(%s,%s)"
        cursor.execute(sql, (index, url))
        db.commit()


pretreatment()
