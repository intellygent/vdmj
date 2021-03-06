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

package com.fujitsu.vdmj.config;

import com.fujitsu.vdmj.util.ConfigBase;

/**
 * The Config class is used to hold global configuration values. The
 * values are read from the vdmj.properties file, and defaults are defined
 * as public statics.
 */
public class Properties extends ConfigBase
{
	/** The tab stop for source files. */
	public static int parser_tabstop = 4;

	/** The maximum number of expansions for "+" and "*" trace patterns. */
	public static int traces_max_repeats = 5;

	/** The default duration for RT statements. */
	public static int rt_duration_default = 2;

	/** The default timeslice (statements executed) for a FCFS policy */
	public static int scheduler_fcfs_timeslice = 10;

	/** The vCPU/vBUS timeslice */
	public static int scheduler_virtual_timeslice = 10000;

	/** The timeslice variation (+/- jitter ticks) */
	public static int scheduler_jitter = 0;

	/** Enable transactional variable updates. */
	public static boolean rt_duration_transactions = false;

	/** Enable InstVarChange RT log entries. */
	public static boolean rt_log_instvarchanges = false;

	/** Maximum period thread overlaps allowed per object */
	public static int rt_max_periodic_overlaps = 20;

	/** Enable extra RT log diagnostics for guards etc. */
	public static boolean diags_guards = false;

	/** Enable extra RT log diagnostics for timesteps. */
	public static boolean diags_timestep = false;

	/**
	 * When the class is initialized, we call the ConfigBase init method, which
	 * uses the properties file passed to update the static fields above.
	 */
	public static void init()
	{
		try
		{
			init("vdmj.properties", Properties.class);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
