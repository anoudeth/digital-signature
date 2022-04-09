### Generate private and public key
```bash
# 1. Generate RSA public-private key pair, 2048 bits. 
$ openssl genrsa -des3 -out keypair.pem 20482

# 2. Export public key in PEM format
$ openssl rsa -in keypair.pem -outform PEM -pubout -out public.pem

# 3. Optionally,export private key in PEM format to use with Java
$ openssl pkcs8 -topk8 -inform PEM -outform pem -in keypair.pem -out private.key -nocrypt

# 4. Submit only public key to IB
```
