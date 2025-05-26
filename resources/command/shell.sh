docker run -d \
  --name nacos-2.3.1 \
  -e MODE=standalone \
  -p 8848:8848 \
  -p 9848:9848 \
  nacos/nacos-server:v2.3.1

docker run -d \
--name mysql-ksf \
--restart unless-stopped \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD="ksf123456" \
mysql:8.0.18