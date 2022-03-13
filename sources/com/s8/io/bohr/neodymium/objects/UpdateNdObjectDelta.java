package com.s8.io.bohr.neodymium.objects;

import java.io.IOException;
import java.util.List;

import com.s8.io.bohr.atom.BOHR_Keywords;
import com.s8.io.bohr.atom.S8Exception;
import com.s8.io.bohr.atom.S8Index;
import com.s8.io.bohr.atom.S8Object;
import com.s8.io.bohr.neodymium.branches.NdBranch;
import com.s8.io.bohr.neodymium.branches.NdOutbound;
import com.s8.io.bohr.neodymium.fields.NdFieldDelta;
import com.s8.io.bohr.neodymium.type.BuildScope;
import com.s8.io.bohr.neodymium.type.NdType;
import com.s8.io.bohr.neodymium.type.NdTypeComposer;
import com.s8.io.bytes.alpha.ByteOutflow;
import com.s8.io.bytes.alpha.MemoryFootprint;


/**
 * 
 * @author pierreconvert
 *
 */
public class UpdateNdObjectDelta extends NdObjectDelta {


	public List<NdFieldDelta> deltas;
	
	
	public NdType type;


	/**
	 * 
	 * @param index
	 * @param type
	 * @param deltas
	 */
	public UpdateNdObjectDelta(S8Index index, NdType type, List<NdFieldDelta> deltas) {
		super(index);
		
		this.type = type;

		// deltas
		this.deltas = deltas;
	}


	@Override
	public void serialize(NdOutbound outbound, ByteOutflow outflow) throws IOException {

		/* retrieve composer */
		NdTypeComposer composer = outbound.getComposer(type.getBaseType());

		/*  advertise diff type: publish a create node */

		/* pass flag */
		outflow.putUInt8(BOHR_Keywords.UPDATE_NODE);

		/* pass index */
		S8Index.write(index, outflow);

		// produce all diffs
		for(NdFieldDelta delta : deltas) {
			int ordinal = delta.getField().ordinal;
			composer.fieldComposers[ordinal].publish(delta, outflow);
		}

		/* Close node */
		outflow.putUInt8(BOHR_Keywords.CLOSE_NODE);
	}



	@Override
	public void consume(NdBranch branch, BuildScope scope) {

		try {
			// retrieve vertex
			NdVertex vertex = branch.vertices.get(index);

			if(vertex==null) {
				throw new S8Exception("failed to retrieve vertex for index: "+index);
			}

			// retrieve object
			S8Object object = vertex.object;

			// consume diff
			type.consumeDiff(object, deltas, scope);	

		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}


	/*
	public static UpdateNdObjectDelta deserialize(LithCodebaseIO codebaseIO, ByteInflow inflow) {
		UpdateNdObjectDelta objectDelta = new UpdateNdObjectDelta();
		objectDelta.deserializeBody(codebaseIO, inflow);
		return objectDelta;
	}
	 */


	@Override
	public void computeFootprint(MemoryFootprint weight) {

		weight.reportInstance();

		// fields
		if(deltas!=null) {
			for(NdFieldDelta delta : deltas) {
				delta.computeFootprint(weight);
			}
		}
	}
}