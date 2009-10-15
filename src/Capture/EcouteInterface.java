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



import java.util.concurrent.CountDownLatch;

import Main.Commun;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;





public class EcouteInterface implements Runnable
{
	private JpcapCaptor jpcap;									//Nouveau Capteur de paquets.
	private NetworkInterface interf;									//Interface a ecouter.
	private CountDownLatch semaClose = new CountDownLatch(1);
	private CountDownLatch semaStart = new CountDownLatch(1);
	
	
	//=====================================================================================================
	
	public EcouteInterface(ListFile fileList, NetworkInterface interf, CountDownLatch semaStart)
	{
		this.interf = interf;	
		this.semaStart = semaStart;	
	}
	
	//=====================================================================================================

	
	
	
	//=====================================================================================================
	
	public void run()
	{

		try 
		{
			this.jpcap = JpcapCaptor.openDevice(interf, 65535, false, 20);
			
		} catch (Exception e) {Commun.logError(e);}

		this.semaStart.countDown();
		
		if (this.jpcap != null) 
		{
			System.out.println("-= TubeMaster++ Started on "+this.interf.description+" =-");
			this.jpcap.setPacketReadTimeout(1000);
			try {this.jpcap.loopPacket(-1,new PacketsReceiver());} catch (Exception e) {}
			this.jpcap.updateStat();
			this.jpcap.close();

		}
		
		System.out.println("-= TubeMaster++ Stopped on "+this.interf.description+" (Rcv="+this.jpcap.received_packets+"|Drp="+this.jpcap.dropped_packets+")=-");
		this.semaClose.countDown();	
	}
	
	//=====================================================================================================
	
	public void shutUp()
	{
		if (this.jpcap != null)
		{
			this.jpcap.breakLoop();
			try 
			{
				this.semaClose.await();
			} catch (InterruptedException e) {Commun.logError(e);}
		}
	}
}