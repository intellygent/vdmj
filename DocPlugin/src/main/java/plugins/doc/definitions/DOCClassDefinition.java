/*******************************************************************************
 *
 *	Copyright (c) 2019 Paul Chisholm
 *
 *	Author: Paul Chisholm
 *
 *	This file is part of VDMJ.
 *
 *	VDMJ is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	VDMJ is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with VDMJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 ******************************************************************************/
 
package plugins.doc.definitions;

import plugins.doc.annotations.DOCAnnotationList;
import plugins.doc.definitions.DOCDefinition;
import plugins.doc.definitions.DOCDefinitionList;
import plugins.doc.lex.DOCNameList;
import plugins.doc.lex.DOCNameToken;

public class DOCClassDefinition extends DOCDefinition
{
	private static final long serialVersionUID = 1L;
	private final DOCNameList supernames;
	private final DOCDefinitionList definitions;

	public DOCClassDefinition(DOCAnnotationList annotations, DOCNameToken name, DOCNameList supernames, DOCDefinitionList definitions)
	{
		super(name.location, DOCAccessSpecifier.DEFAULT, name);
		this.supernames = supernames;
		this.definitions = definitions;
	}

	@Override
	public void extent(int maxWidth)
	{
		return;
	}
	
	@Override
	public String toHTML(int indent)
	{
		return null;
	}
}
