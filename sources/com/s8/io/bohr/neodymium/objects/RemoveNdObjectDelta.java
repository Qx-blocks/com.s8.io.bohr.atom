package com.s8.io.bohr.neodymium.objects;

import java.io.IOException;

import com.s8.io.bohr.atom.BOHR_Keywords;
import com.s8.io.bohr.atom.S8Index;
import com.s8.io.bohr.neodymium.branches.NdBranch;
import com.s8.io.bohr.neodymium.branches.NdOutbound;
import com.s8.io.bohr.neodymium.exceptions.NdIOException;
import com.s8.io.bohr.neodymium.type.BuildScope;
import com.s8.io.bytes.alpha.ByteOutflow;
import com.s8.io.bytes.alpha.MemoryFootprint;

public class RemoveNdObjectDelta extends NdObjectDelta {

	public RemoveNdObjectDelta(S8Index index) {
		super(index);
	}

	@Override
	public void consume(NdBranch branch, BuildScope scope) throws NdIOException {
		
	}

	@Override
	public void serialize(NdOutbound outbound, ByteOutflow outflow) throws IOException {
		
		/* remove node */
		outflow.putUInt8(BOHR_Keywords.REMOVE_NODE);

		/* define index */
		S8Index.write(index, outflow);
	}
	

	@Override
	public void computeFootprint(MemoryFootprint weight) {
		// TODO Auto-generated method stub
		
	}

}