/*******************************************************************************
 *
 *	Copyright (c) 2016 Fujitsu Services Ltd.
 *
 *	Author: Nick Battle
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

package com.fujitsu.vdmj.ast.definitions;

import com.fujitsu.vdmj.ast.expressions.ASTExpression;
import com.fujitsu.vdmj.ast.lex.LexNameToken;
import com.fujitsu.vdmj.ast.types.ASTType;

/**
 * A class to represent assignable variable definitions.
 */
public class ASTAssignmentDefinition extends ASTDefinition
{
	private static final long serialVersionUID = 1L;

	public final ASTType type;
	public final ASTExpression expression;

	public ASTAssignmentDefinition(LexNameToken name, ASTType type, ASTExpression expression)
	{
		super(name.location, name);
		this.type = type;
		this.expression = expression;
	}

	@Override
	public String toString()
	{
		return name + ":" + type + " := " + expression;
	}

	@Override
	public String kind()
	{
		return "assignable variable";
	}
}
