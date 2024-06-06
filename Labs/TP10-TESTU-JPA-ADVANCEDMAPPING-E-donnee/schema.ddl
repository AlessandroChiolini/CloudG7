create table Agence (id bigint not null, codePostal varchar(255), rue varchar(255), ville varchar(255), primary key (id))
create table Banquier (id bigint not null, email varchar(255), agence_id bigint, primary key (id))
create table Client (id bigint not null, description varchar(255), codePostal varchar(255), rue varchar(255), ville varchar(255), agence_id bigint, primary key (id))
create table Compte (id bigint not null, description varchar(255), primary key (id))
create table CompteExterne (id bigint not null, numeroRib varchar(255), FK_CLIENT bigint, primary key (id))
create table CompteInterne (id bigint not null, numero varchar(255), solde float not null, primary key (id))
create table CompteInterne_Client (comptesInternes_id bigint not null, proprietaires_id bigint not null, primary key (comptesInternes_id, proprietaires_id))
create table Operation (id bigint not null, intitule varchar(255), montant bigint not null, date timestamp, FK_COMPTEINTERNE bigint, primary key (id))
create table Personne (id bigint not null, nom varchar(255), prenom varchar(255), primary key (id))
alter table Banquier add constraint FK956E3FA3C25A15AC foreign key (agence_id) references Agence
alter table Banquier add constraint FK956E3FA35A480DF9 foreign key (id) references Personne
alter table Client add constraint FK7877DFEBC25A15AC foreign key (agence_id) references Agence
alter table Client add constraint FK7877DFEB5A480DF9 foreign key (id) references Personne
alter table CompteExterne add constraint FK4811CEC5FB3CD5E2 foreign key (FK_CLIENT) references Client
alter table CompteExterne add constraint FK4811CEC52C84386D foreign key (id) references Compte
alter table CompteInterne add constraint FKA9A45932C84386D foreign key (id) references Compte
alter table CompteInterne_Client add constraint FK2572BC172BF359C8 foreign key (comptesInternes_id) references CompteInterne
alter table CompteInterne_Client add constraint FK2572BC17E4E36A8 foreign key (proprietaires_id) references Client
alter table Operation add constraint FKDA8CF5471F7837BA foreign key (FK_COMPTEINTERNE) references CompteInterne
create table dual_hibernate_sequence (zero integer)
insert into dual_hibernate_sequence values (0)
create sequence hibernate_sequence start with 1
