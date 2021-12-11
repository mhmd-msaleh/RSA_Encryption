# RSA_Encryption


 
I. Initial Step (Prepare the program):
    1. Open a shell terminal and navigate to RSA_Encryption directory.  
    2. Compile the java classes, use the following command: 
```console
javac RSA.java EncryptionDriver.java DecryptionDriver.java 
```

II. For Encryption:
    1. Please use the file named file.txt (if not found, make one) located in "files" directory and include the following:
        - The first line must contains the public keys e and n respectively, seperated be aspace.
        - The second line onward contains the text to by encrypted, you may use a newline, space, and any of the these characters. 
            ('.', '?', '!', ',', ';', ':', '-', '(', ')', '{', '}', '[', ']', single quotes, double quotes)
    2. Run EncryptionDriver, to do that use the following command. 
```console
java EncryptionDriver``` 
    3. The encrypted text will be in files/file.rsa file. 

For Decryption: 
    1. Use the file named file.rsa (if not found, make one) located in "files" directory, and include the following: 
    - The encrypted text (without white spaces) consisting of only numbers. 
    2. Run DecryptionDriver, to do that use the follwing command:   
```console
java DecryptionDriver```
    3. To decrypt the sample file.txt provided use d = 2832537816223, n = 99808491759239. 
    4. The decrypted file will be in files/file.dec file. 

Please note: In order for the program to run successfully, it is important to include all text files in "files" directory. 

