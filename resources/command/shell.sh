docker run -d \
--name mysql-ksf \
--restart unless-stopped \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD="ksf123456" \
mysql:8.0.18