package casestudy1;

class Multi1 extends Thread{
	String[] ptr;
	public Multi1(String[] ptr){
		this.ptr=ptr;
	}
	public void run(){
		int y=30;
		if(y==20){
			System.out.println("y is 30");
			
		}
		else{
			System.out.println(ptr.length);
		}
	}
}

class Multi extends Thread{ 
	static String[] ptr;
public void run(){  
int x=20;

if(x==10){
	ptr=null;
}
else{
	ptr=new String[10];
}
System.out.println(ptr.length);

}  
public static void main(String args[]) throws InterruptedException{  
Multi t1=new Multi();  
Multi1 t2=new Multi1(ptr);
t1.start(); 
t2.start();
t1.join();
t2.join();
 }  
}


