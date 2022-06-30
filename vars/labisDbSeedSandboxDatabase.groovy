def call() {
	fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
#
# Install the MySQL driver
#   gem install mysql2
#
# Ensure the MySQL gem is defined in your Gemfile
#   gem \'mysql2\'
#
# And be sure to use new-style password hashing:
#   https://dev.mysql.com/doc/refman/5.7/en/password-hashing.html
#
default: &default
  adapter: mysql2
  encoding: utf8mb4
  pool: <%= ENV.fetch("RAILS_MAX_THREADS") { 5 } %>

labis_vanilla_test:
  <<: *default
  database: lmo_tpcm_production
  username: <%= ENV["MYSQL_USER_NAME"] %>
  password: <%= ENV["MYSQL_USER_PASSWORD"] %>
  host: 10.222.12.20

labis_vanilla_mercator_test:
  <<: *default
  database: mercator_production
  username: <%= ENV["MYSQL_USER_NAME"] %>
  password: <%= ENV["MYSQL_USER_PASSWORD"] %>
  host: 10.222.12.20

production:
  <<: *default
  database: lmo_tpcm_production
  username: <%= ENV["MYSQL_USER_NAME"] %>
  password: <%= ENV["MYSQL_USER_PASSWORD"] %>
  host: 10.222.12.20
''', fileName: 'config/database.yml')])

}
