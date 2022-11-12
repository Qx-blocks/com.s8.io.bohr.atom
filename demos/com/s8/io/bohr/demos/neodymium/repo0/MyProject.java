package com.s8.io.bohr.demos.neodymium.repo0;

import com.s8.io.bohr.atom.S8Ref;
import com.s8.io.bohr.atom.annotations.S8Field;
import com.s8.io.bohr.atom.annotations.S8ObjectType;
import com.s8.io.bohr.lithium.object.LiObject;

/**
 * 
 * @author pierreconvert
 *
 */
@S8ObjectType(name = "Dclib/MyProject")
public class MyProject extends LiObject {


	public MyProject() {
		super();
	}


	public @S8Field(name = "field1") int specialField;

	/**
	 * the visco
	 */
	public @S8Field(name = "viscosity") double visc;


	/** 
	 * the text for the data
	 */
	public @S8Field(name = "message") String txt;

	public @S8Field(name = "payload") MyProjectPayload payload;









	public @S8Field(name = "last") S8Ref<MyProject> last;



	public void setViscosity(double val) {
		visc = val;
	}


	public void setPayload(int choice) {
		payload = new CarPayload();
	}


}
