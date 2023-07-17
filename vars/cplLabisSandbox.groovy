def database() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
production:
  adapter: mysql2
  database: lmo_tpcm_production
  username: <%= ENV["MYSQL_USER_CREDS"].split(':')[0] %>
  password: <%= ENV["MYSQL_USER_CREDS"].split(':')[1] %>
  host: ussigatxmys004t.us.int.sonichealthcare
  port: 3307
  reconnect: true

mercator_production:
  adapter: mysql2
  database: mercator
  username: <%= ENV["MYSQL_USER_CREDS"].split(':')[0] %>
  password: <%= ENV["MYSQL_USER_CREDS"].split(':')[1] %>
  host: ussigatxmys004t.us.int.sonichealthcare
  port: 3307
  reconnect: true
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
    host: 10.212.148.40
    port: 8081
  mongo_server:
    host: 10.212.148.40
    port: 8088
  req_viewer:
    host: 10.212.148.50
    port: 8082
  redis_server:
    host: 10.212.148.40
    port: 6379
  labis_v2:
    host: v2qa.labis.net
    port: 443
  labis_api:
    host: apiqa.labis.net
    port: 443
  device_server:
    host: localhost
    port: 9999
  ssrs_server:
    host: https://ssrsdemo.labis.net
    folder: "sandbox"
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
