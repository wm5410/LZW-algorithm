How to run:
After compining all the files with 
javac *.java
Run the program with the following command 
java ByteToHex input.txt | java LZWencode | java LZWdecode | java HexToByte > output.txt

This will take the input file (input.txt) and output it into a new output file (output.txt)

Each Java program in the pipeline reads input from the standard input stream (System.in) and writes output to the standard output stream (System.out). 
When you use the pipe (|) operator in the command line, it connects the standard output of one command to the standard input of the next command.


----------------------------------------------------------------------------------------------------------------------------------------------------------


Steps taken to build program:
ByteToHex: This step converts the input bytes into hexadecimal representation. This is necessary because the subsequent LZW encoding process operates on hexadecimal data. 
By converting bytes to hex early in the process, we ensure that the input data is in the correct format for LZW encoding.
https://mkyong.com/java/java-how-to-convert-bytes-to-hex/
This website gave me inspiration for the byte to hex program

LZWencode: After the data has been converted to hexadecimal format, we apply the LZW encoding algorithm. This algorithm operates on a stream of input symbols and generates a compressed representation. 
By performing LZW encoding after hexadecimal conversion, we ensure that the algorithm operates on the correct input data format.

LZWdecode: With the data now in its original packed format, we can apply the LZW decoding algorithm to recover the original uncompressed data. 
Decoding is performed after unpacking to ensure that the algorithm operates on the correct data format and can accurately reconstruct the original input.

HexToByte: Finally, we convert the decoded data from hexadecimal back to bytes. 
This step is necessary to obtain the final output in its original byte format, which may be required for further processing or storage.



