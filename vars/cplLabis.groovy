def database() {
	fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
production:
  adapter: mysql2
  database: cpl_enterprise_demo
  username: <%= ENV["MYSQL_USER_NAME"] %>
  password: <%= ENV["MYSQL_USER_PASSWORD"] %>
  host: 10.212.148.33
  reconnect: true

mercator_production:
  adapter: mysql2
  database: mercator_demo
  username: <%= ENV["MYSQL_USER_NAME"] %>
  password: <%= ENV["MYSQL_USER_PASSWORD"] %>
  host: mysqldb
''', fileName: 'config/database.yml')])

}

def secrets() {
	fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  secret: 9afebc7fffcade1cca5ce986cf00cc9eeb82c7b720bbd5b2d66dbf873b354f571f35b584d87649ca50d13e495e61977f5c2ede28baccdbbc96c588454c06cce6

''', fileName: 'config/secrets.yml')])
}

def queue_server() {
	fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  host: rabbitmqapp
  username: labis
  password: L@b1$
  enabled: false

''', fileName: 'config/queue_server.yml')])
}

def servers() {
	fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  slide_server:
    host: labeler
    port: 3000
  mongo_server:
    host: 192.168.3.103
    port: 8091
  redis_server:
    host: redisdb
    port: 6379
  device_server:
    host: localhost
    port: 9999
  labis_v2:
    host: v2qa.labis.net
    port: 443
  labis_api:
    host: apiqa.labis.net
    port: 443
  ssrs_server:
    host: https://ssrsdemo.labis.net
    folder: sandbox
    user: administrator
    password: password

''', fileName: 'config/servers.yml')])
}

def memcached() {
	fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
defaults:
  readonly: false
  urlencode: false
  c_threshold: 10000
  compression: true
  debug: false
  fragments: false
  memory: 256
  expires: 89400
  timeout: 3

production:
  namespace: "mem-production"
  memory: 256
  benchmarking: false
  servers: memcached:11211

''', fileName: 'config/memcached.yml')])
}

