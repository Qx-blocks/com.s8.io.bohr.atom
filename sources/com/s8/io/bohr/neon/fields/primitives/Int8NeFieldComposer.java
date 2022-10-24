package com.s8.io.bohr.neon.fields.primitives;

import java.io.IOException;

import com.s8.io.bohr.BOHR_Types;
import com.s8.io.bohr.neon.core.BuildScope;
import com.s8.io.bohr.neon.core.NeObjectTypeHandler;
import com.s8.io.bohr.neon.fields.NeFieldValue;
import com.s8.io.bytes.alpha.ByteInflow;
import com.s8.io.bytes.alpha.ByteOutflow;


/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class Int8NeFieldComposer extends PrimitiveNeFieldComposer {

	
	public final static long SIGNATURE = BOHR_Types.INT8;

	public @Override long getSignature() { return SIGNATURE; }
	


	public Int8NeFieldComposer(NeObjectTypeHandler prototype, String name) {
		super(prototype, name);
	}


	@Override
	public void publishEncoding(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(BOHR_Types.UINT8);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public int get(NeFieldValue wrapper) {
		return ((Value) wrapper).value;
	}
	
	
	/**
	 * 
	 * @param values
	 * @param value
	 */
	public void set(NeFieldValue wrapper, int value) {
		((Value) wrapper).setValue(value);
	}
	
	

	@Override
	public NeFieldValue createValue() {
		return new Value();
	}

	
	
	/**
	 * 
	 * @author pierreconvert
	 *
	 */
	public static class Value extends PrimitiveNeFieldComposer.Value {
		
		private int value;
	
		public Value() {
			super();
		}
		
		public void setValue(int value) {
			this.value = value;
			this.hasDelta = true;
		}
		
		@Override
		public void compose(ByteOutflow outflow) throws IOException {
			outflow.putUInt8(value);
		}

		@Override
		public void parse(ByteInflow inflow, BuildScope scope) throws IOException {
			value = inflow.getUInt8();
		}
	}
}