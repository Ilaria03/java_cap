using { my.bookshop, sap.common } from '../db/data-model';

service CatalogService {
  entity Domande as projection on bookshop.Domande;
  entity Risposte as projection on bookshop.Risposte;
}