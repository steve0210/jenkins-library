def database {
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

def secrets {
	fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.

# Your secret key is used for verifying the integrity of signed cookies.
# If you change this key, all old signed cookies will become invalid!

# Make sure the secret is at least 30 characters and all random,
# no regular words or you'll be exposed to dictionary attacks.
# You can use `rake secret` to generate a secure secret key.

# Make sure the secrets in this file are kept private
# if you're sharing your code publicly.

# Do not keep production secrets in the repository,
# instead read values from the environment.
production:
  secret_key_base: b7a44d025c73747e8ea8f4f4bfe2aa54570274d1da4eaa982ea51d7440ed7268e58d4d11ff6b9220860bcf043dae6ca06a7ab930314ab85b499b2b820f309030
''', fileName: 'config/secrets.yml')])
}
