// //CONSIDER
// // import java.io.File;
// // import java.nio.file.Files;
// // File file;
// // // ...(file is initialised)...
// // byte[] fileContent = Files.readAllBytes(file.toPath());
// // // Converting the bytes into hex requires you to break them into
// // for each byte in fileContent{
// // print Integer.toHexString(byte);
// // }


// // Guava third party library
// // import ByteStreams.toByteArray;
// // tutorial 5: compression 7
// // byte[] data = ByteStreams.toByteArray(System.in);
// // // Alternatively
// // BufferedReader reader = new BufferedReader(new InputStreamReader
// // someByte = reader.read(); // Reads the next character (8 bits) a
// // hexValue = Integer.toHexString(someByte);
// // public class basicTranslator {




how to run:
to test byte to hex to byte 
cat cat.jpg | java ByteToHex | java HexToByte > catout.jpg


the final output should be 
java ByteToHex {filename} | java LZWencode | java LZWpack | java LZWunpack | java LZWdecode | java HexToByte > {output file}


Each Java program in the pipeline reads input from the standard input stream (System.in) and writes output to the standard output stream (System.out). 
When you use the pipe (|) operator in the command line, it connects the standard output of one command to the standard input of the next command.


1. Initialise the initial dictionary with 0 -> F 
2. fill in dictionary








----------------------------------------------------------------------------------------------------------------------------------------------------------------

STEPS to take:
ByteToHex: This step converts the input bytes into hexadecimal representation. This is necessary because the subsequent LZW encoding process operates on hexadecimal data. 
By converting bytes to hex early in the process, we ensure that the input data is in the correct format for LZW encoding.
https://mkyong.com/java/java-how-to-convert-bytes-to-hex/


LZWencode: After the data has been converted to hexadecimal format, we apply the LZW encoding algorithm. This algorithm operates on a stream of input symbols and generates a compressed representation. 
By performing LZW encoding after hexadecimal conversion, we ensure that the algorithm operates on the correct input data format.

LZWpack: This step packs the encoded data into a more compact representation. Packing the data reduces the number of bits required to represent each symbol, leading to further compression. 
It is performed after LZW encoding to ensure that we are packing the already compressed data, resulting in more efficient storage and transmission.

LZWunpack: To reverse the packing process and restore the data to its original form, we need to unpack the data first. 
Unpacking is performed before LZW decoding to ensure that the data is in its original packed format before applying the decoding algorithm.

LZWdecode: With the data now in its original packed format, we can apply the LZW decoding algorithm to recover the original uncompressed data. 
Decoding is performed after unpacking to ensure that the algorithm operates on the correct data format and can accurately reconstruct the original input.

HexToByte: Finally, we convert the decoded data from hexadecimal back to bytes. 
This step is necessary to obtain the final output in its original byte format, which may be required for further processing or storage.



input texts:
The quick brown fox jumped over the lazy dog.


62616e616e616e616e616e61

62
26
61
16
6e
e6
616
6e6
616e