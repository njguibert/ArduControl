package Clases;
import Vistas.Interfaz;



public class Arduino {
	Interfaz i;
	
	ConexionSerial serialCom = new ConexionSerial(this);
	public String dato;
	public String valor;
	
	public Arduino(){
		serialCom.initialize();

		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Comenzo la transmicion...");
		
	}
	public void setInterfaz(Interfaz in) {
		i=in;
	}
	
	public void SendData(String data){
		serialCom.sendData(data);
	}
	
	public void recivedData(String data){
		int token=data.indexOf(":");
		dato=data.substring(0,token);
		
		valor=(data.substring(token+1,data.length()));
		
	 System.out.println("Recibi de Arduino:" + data);
	 i.actualizarmonitor();

	}
	
	}




 
