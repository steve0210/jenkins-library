def call() {
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
