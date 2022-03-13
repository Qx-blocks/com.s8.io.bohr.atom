package com.s8.io.bohr.atom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface S8Param {

	
	/**
	 * 
	 * @return the name this field will be bound to
	 */
	public String name();
	
	
	/**
	 * 
	 * @return the chosen I/O format for exchanging data
	 */
	public String flow() default "(default)";
	
	
	/**
	 * 
	 * @return a Bool64 
	 */
	public long props() default 0L;
	
	public long mask() default 0x00L;

}
