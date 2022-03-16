package com.s8.io.bohr.neon.methods.primitives;

import java.io.IOException;

import com.s8.io.bohr.BOHR_Types;
import com.s8.io.bohr.neon.core.NeBranch;
import com.s8.io.bohr.neon.core.NeObjectPrototype;
import com.s8.io.bohr.neon.methods.NeFunc;
import com.s8.io.bohr.neon.methods.NeMethod;
import com.s8.io.bohr.neon.methods.NeRunnable;
import com.s8.io.bytes.alpha.ByteInflow;

/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class IntegerNeMethod extends NeMethod {


	public interface Lambda {
		
		public void operate(int arg);
		
	}
	

	public final static long SIGNATURE = BOHR_Types.INT32;

	public @Override long getSignature() { return SIGNATURE; }

	
	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public IntegerNeMethod(NeObjectPrototype prototype, String name) {
		super(prototype, name);
	}



	

	@Override
	public NeRunnable buildRunnable(ByteInflow inflow) throws IOException {
		switch(inflow.getUInt8()) {
		case BOHR_Types.UINT8 : new UInt8NeRunnable();
		case BOHR_Types.UINT16 : new UInt16NeRunnable();
		case BOHR_Types.UINT32 : new UInt32NeRunnable();
		case BOHR_Types.INT8 : new Int8NeRunnable();
		case BOHR_Types.INT16 : new Int16NeRunnable();
		case BOHR_Types.INT32 : new Int32NeRunnable();
		default : throw new IOException("Unsupported type");
		}
	}
	
	
	private static class UInt8NeRunnable implements NeRunnable {

		@Override
		public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
			int arg = inflow.getUInt8();
			((Lambda) (func.lambda)).operate(arg);
		}
	}
	
	
	private static class UInt16NeRunnable implements NeRunnable {

		@Override
		public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
			int arg = inflow.getUInt16();
			((Lambda) (func.lambda)).operate(arg);
		}
	}

	

	
	private static class UInt32NeRunnable implements NeRunnable {

		@Override
		public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
			int arg = (int) inflow.getUInt32();
			((Lambda) (func.lambda)).operate(arg);
		}
	}

	
	private static class Int8NeRunnable implements NeRunnable {

		@Override
		public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
			int arg = inflow.getInt8();
			((Lambda) (func.lambda)).operate(arg);
		}
	}
	
		
	private static class Int16NeRunnable implements NeRunnable {

		@Override
		public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
			int arg = inflow.getInt16();
			((Lambda) (func.lambda)).operate(arg);
		}
	}
	

	
	private static class Int32NeRunnable implements NeRunnable {

		@Override
		public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
			int arg = inflow.getInt32();
			((Lambda) (func.lambda)).operate(arg);
		}
	}
}
