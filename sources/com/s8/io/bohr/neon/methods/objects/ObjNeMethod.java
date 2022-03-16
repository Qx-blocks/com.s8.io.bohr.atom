package com.s8.io.bohr.neon.methods.objects;

import java.io.IOException;

import com.s8.io.bohr.BOHR_Types;
import com.s8.io.bohr.neon.core.NeBranch;
import com.s8.io.bohr.neon.core.NeObject;
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
public class ObjNeMethod<T extends NeObject> extends NeMethod {


	public interface Lambda<T extends NeObject> {
		
		public void operate(T arg);
		
	}
	

	public final static long SIGNATURE = BOHR_Types.S8OBJECT;

	public @Override long getSignature() { return SIGNATURE; }

	
	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public ObjNeMethod(NeObjectPrototype prototype, String name) {
		super(prototype, name);
	}




	@Override
	public NeRunnable buildRunnable(ByteInflow inflow) throws IOException {
		switch(inflow.getUInt8()) {
		case BOHR_Types.S8OBJECT : new S8ObjectNeRunnable<T>();
		default : throw new IOException("Unsupported type");
		}
	}
	
	
	private static class S8ObjectNeRunnable<T extends NeObject> implements NeRunnable {

		@SuppressWarnings("unchecked")
		@Override
		public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
			String index = inflow.getStringUTF8();
			NeObject arg = index != null ? branch.getVertex(index) : null;
			((Lambda<T>) (func.lambda)).operate((T) arg);
		}
	}
}
