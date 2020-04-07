package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	
	public static BigInteger hashOf(String entity) {		
		
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		
		try {
			// we use MD5 with 128 bits digest
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			// compute the hash of the input 'entity'
			byte[] entityBytes = entity.getBytes();
			md.update(entityBytes);
			byte[] digest = md.digest();
			
			// convert the hash into hex format
			String val = toHex(digest);
			
			// convert the hex into BigInteger
			hashint = new BigInteger(val, 16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// return the BigInteger
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("MD5");
			
			// get the digest length
			int digestLength = md.getDigestLength();
			
			// compute the number of bits = digest length * 8
			int noOfBits = digestLength * 8;
			
			// compute the address size = 2 ^ number of bits
			BigInteger addressSize = new BigInteger("2").pow(noOfBits);
			
			return addressSize;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		
		// find the digest length
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digestlen = md.getDigestLength();
		
		return digestlen * 8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
