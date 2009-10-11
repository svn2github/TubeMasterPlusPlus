/*
 * TubeMaster++ - An Internet Multimedia Capture Tool.
 * Copyright (C) 2009 GgSofts
 * Contact: admin@tubemaster.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package Capture;

import java.awt.Image;
import java.awt.Toolkit;



public class FileFormat 
{
	
	private String format;		//Chaine représentant le format.
	private Image formatLogo;	//Image représentant le format.
	
	//=====================================================================================================
	
	public FileFormat(String format)
	{
		this.format = format;
		this.formatLogo = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/"+format.toLowerCase()+"_format.png"));	
	}
	
	//=====================================================================================================
	
	
	
	
	//=====================================================================================================
	
	public String retFormat() {return this.format;}
	public Image retFormatLogo() {return this.formatLogo;}
	
	//=====================================================================================================
	

}
