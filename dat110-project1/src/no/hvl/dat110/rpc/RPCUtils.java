package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;


import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageConfig;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];

		// TODO: marshall RPC identifier and string into byte array

		encoded[0] = rpcid;
		byte[] string = str.getBytes();
		
		for(int i = 0; i < string.length; i++) {
			encoded[i + 1] = string[i];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded;

		// TODO: unmarshall String contained in data into decoded

		byte[] code = new byte[MessageConfig.SEGMENTSIZE];
		 
		for(int i = 0; i < data.length - 1 ; i++) {
			code[i] = data[i + 1];
		}
		
		decoded = new String(code).trim();

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];

		// TODO: marshall RPC identifier in case of void type
		
		encoded[0] = rpcid;


		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
		
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded = new byte[5];

		// TODO: marshall RPC identifier and string into byte array

		encoded[0] = rpcid;
		
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(x);
		
		for(int i = 0; i < encoded.length - 1; i++) {
			encoded[i + 1] = buffer.get(i); 
		}
		 

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;

		// TODO: unmarshall integer contained in data

		ByteBuffer buffer = ByteBuffer.allocate(4);
		
		for(int i = 0; i < data.length - 1; i++) {
			buffer.put(i, data[i + 1]);
		}
		
		decoded = buffer.getInt();
		return decoded;

	}
}
