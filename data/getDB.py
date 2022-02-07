import xml.dom.minidom
import pymysql


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
    return pymysql.connect(host=host, user=user, password=password, database=database, charset=charset, port=int(port))
