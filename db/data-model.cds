namespace my.bookshop;
using { User, Country, managed } from '@sap/cds/common'; 

entity Risposte {
  key ID  : UUID;
  testo    : String;
  corretta : Boolean;
  domanda : Association to Domande;
}

entity Domande   {
  key ID  : UUID;
  testo    : String;
  risposte : Association to many Risposte on risposte.domanda = $self;
}
