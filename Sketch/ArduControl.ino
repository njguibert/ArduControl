#include <Stepper.h>
#define PASOS 200 //Cantidad de Pasos del Motor
Stepper stepper(PASOS,8,9,10,11);
String dato;
String valor;

bool token=false;

String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete

void setup() {
  Serial.begin(9600);
  pinMode(13,OUTPUT);
  stepper.setSpeed(30);
}

void loop() {
  if (stringComplete) { 
    EjecutarAccion(dato,valor); //Cuando termino de leer el buffer ejecuto la orden
    stringComplete = false;
    dato ="";
    valor="";
  }
  serialEvent();
}

/*
Verifica existencia de informacion en el buffer
*/
void serialEvent() {
  while (Serial.available() > 0) { //Recorro los bytes y los convierto a String
    //Obtengo un byte y lo convierto a caracter
    char letra = (char)Serial.read(); 
    if (letra!=':'){
        if(!token) dato +=letra;
        else valor +=letra;           
      } 
    else {
      token=true;
    }
    //Si es el ultimo caracter finalizo
    if (letra == '\n') {
      stringComplete = true;
      token=false;
    }    
  }
}


void EjecutarAccion(String d,String v){
   d.trim();
   v.trim();
   Serial.println(d+":"+v); //Aviso que estoy haciendo
   if (d=="LED"){
    digitalWrite(13,HIGH);
     if (v=="1") digitalWrite(13,HIGH);
     else digitalWrite(13,LOW);     
   }
   if (d=="MOTOR"){
     int pasos=v.toInt();
     stepper.step(pasos);
   }
  
  
}
