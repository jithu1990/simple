package casestudy1;

class Multi extends Thread{  
public void run(){  
int x=20;
String[] ptr;
if(x==20){
	ptr=null;
}
else{
	ptr=new String[10];
}
System.out.println(ptr.length);

}  
public static void main(String args[]){  
Multi t1=new Multi();  
t1.start();  
 }  
} 
