package com.s8.io.bohr.neon.methods.primitives;

import java.io.IOException;

import com.s8.io.bohr.atom.BOHR_Types;
import com.s8.io.bohr.neon.core.NeBranch;
import com.s8.io.bohr.neon.core.NeObjectPrototype;
import com.s8.io.bohr.neon.methods.NeFunc;
import com.s8.io.bohr.neon.methods.NeMethod;
import com.s8.io.bytes.alpha.ByteInflow;

/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class Bool8NeMethod extends NeMethod {


	public interface Lambda {
		
		public void operate(boolean arg);
		
	}
	

	public final static long SIGNATURE = BOHR_Types.BOOL8;

	public @Override long getSignature() { return SIGNATURE; }

	
	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public Bool8NeMethod(NeObjectPrototype prototype, String name) {
		super(prototype, name);
	}


	@Override
	public void run(NeBranch branch, ByteInflow inflow, NeFunc func) throws IOException {
		boolean arg = inflow.getBool8();
		((Lambda) (func.lambda)).operate(arg);
	}
	
}
