-- CLIENTES --

insert into Cliente (nome, email, telemovel) values ('Cynthia Fenne', 'cfenne0@uiuc.edu', '6196314992');
insert into Cliente (nome, email, telemovel) values ('Efrem Shuker', 'eshuker1@wikia.com', '6301202995');
insert into Cliente (nome, email, telemovel) values ('Traci Baltrushaitis', 'tbaltrushaitis2@hostgator.com', '9602507117');
insert into Cliente (nome, email, telemovel) values ('Noemi Passfield', 'npassfield3@diigo.com', '5824184843');
insert into Cliente (nome, email, telemovel) values ('Mill Eisold', 'meisold4@harvard.edu', '6751485961');
insert into Cliente (nome, email, telemovel) values ('Bliss Lenormand', 'blenormand5@privacy.gov.au', '1784898437');
insert into Cliente (nome, email, telemovel) values ('Valenka Giorgiutti', 'vgiorgiutti6@homestead.com', '8043853377');
insert into Cliente (nome, email, telemovel) values ('Benny Madgwich', 'bmadgwich7@dion.ne.jp', '6997461809');
insert into Cliente (nome, email, telemovel) values ('Maxie Sawle', 'msawle8@wordpress.com', '9179577082');

-- UtilizadorS --

insert into Utilizador (nome, tipo, password, email, telemovel) values ('Donn Chaperling',    'barca', 'v', 'dchaperling0@ning.com', '327-200-5486');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Kelwin Kas',         'barca', 'v', 'kkas1@wordpress.org', '516-740-2319');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Rosalinde Roistone', 'barca', 'f', 'rroistone2@senate.gov', '331-726-7035');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Calida Ferrillio',   'barca', 'f', 'cferrillio3@edublogs.org', '581-996-1365');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Aile Glenister',     'barca', 'f', 'aglenister4@theglobeandmail.com', '919-728-3706');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Marcello Schust',    'barca', 'f', 'mschust5@gravatar.com', '142-638-6615');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Barca',              'barca', 'a', 'eormes6@toplist.cz', '393-431-6397');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Rosamund Amies',     'barca', 'f', 'ramies7@canalblog.com', '302-309-9053');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Eldridge Neylan',    'barca', 'f', 'eneylan8@hibu.com', '782-967-6449');
insert into Utilizador (nome, tipo, password, email, telemovel) values ('Bale Catcherside',   'barca', 'f', 'bcatcherside9@huffingtonpost.com', '306-665-1780');

-- Item --

insert into Item (nome, preco, stock, tipo) values ('Porsche', 'Boliviano', 18, 'M');
insert into Item (nome, preco, stock, tipo) values ('Lincoln', 'Euro', 23, 'XS');
insert into Item (nome, preco, stock, tipo) values ('Suzuki', 'Euro', 21, '3XL');
insert into Item (nome, preco, stock, tipo) values ('Mercury', 'Rupiah', 27, 'L');
insert into Item (nome, preco, stock, tipo) values ('Buick', 'Euro', 11, 'XL');
insert into Item (nome, preco, stock, tipo) values ('Daihatsu', 'Dollar', 21, '2XL');
insert into Item (nome, preco, stock, tipo) values ('Ford', 'Peso', 14, 'M');
insert into Item (nome, preco, stock, tipo) values ('GMC', 'Yuan Renminbi', 22, 'XS');
insert into Item (nome, preco, stock, tipo) values ('Honda', 'Baht', 11, 'XS');
insert into Item (nome, preco, stock, tipo) values ('Aston Martin', 'Yuan Renminbi', 11, 'XL');

-- Incompatibilidade --

insert into Incompatibilidade (Item_idItem1, Item_idItem2) values (1, 5);
insert into Incompatibilidade (Item_idItem1, Item_idItem2) values (2, 4);
insert into Incompatibilidade (Item_idItem1, Item_idItem2) values (3, 7);
insert into Incompatibilidade (Item_idItem1, Item_idItem2) values (3, 9);

-- DEPENDÊNCIAS --

insert into Dependencia (Item_idItem1, Item_idItem2) values (1, 8);
insert into Dependencia (Item_idItem1, Item_idItem2) values (1, 9);
insert into Dependencia (Item_idItem1, Item_idItem2) values (3, 4);
insert into Dependencia (Item_idItem1, Item_idItem2) values (4, 5);

-- PACOTE --

insert into Pacote (desconto, nome) values (53, '3XL');
insert into Pacote (desconto, nome) values (87, '2XL');
insert into Pacote (desconto, nome) values (89, '3XL');
insert into Pacote (desconto, nome) values (3, '3XL');


-- Item DE PACOTE --

insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 1);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 2);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 3);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 4);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 5);

insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (2, 1);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (2, 2);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (2, 3);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (2, 4);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (2, 5);

insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 1);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 2);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 3);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 4);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 5);

insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (4, 6);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (4, 7);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (4, 8);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (4, 9);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (4, 1);


-- CONFIGURAÇÃO -- 

insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('EX', 'Aquamarine', 'M', 3839, 2, 5);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('TrailBlazer', 'Green', 'XL', 1546, 1, 7);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('900', 'Khaki', 'XL', 3261, 2, 2);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('Firefly', 'Puce', '3XL', 4275, 1, 10);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('Trooper', 'Aquamarine', 'S', 2205, 1, 6);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('Chariot', 'Puce', '3XL', 3319, 2, 5);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('3500', 'Teal', 'XS', 1095, 2, 4);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('Sierra 3500', 'Aquamarine', 'S', 2699, 2, 5);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('Land Cruiser', 'Pink', 'M', 703, 1, 8);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('HHR', 'Orange', 'XS', 3329, 2, 10);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('E250', 'Turquoise', '2XL', 817, 1, 7);
insert into Configuracao (modelo, cor, validade, orcamento, Utilizador_idUtilizador, Cliente_idCliente) values ('Sorento', 'Mauv', '2XL', 956, 2, 5);


-- Item DE CONFIGURAÇÃO -- 

insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (10, 5);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (6, 4);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (11, 7);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (7, 8);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (10, 2);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (12, 10);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 1);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (7, 9);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (7, 10);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (6, 2);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (8, 1);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (11, 3);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 2);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (9, 8);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 2);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 5);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 6);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (5, 10);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 7);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (6, 3);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 7);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (11, 9);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 9);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (11, 6);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 9);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (10, 7);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (11, 2);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 10);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (7, 1);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 2);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (9, 9);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (9, 3);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (9, 5);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 8);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (5, 2);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 10);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 1);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (6, 9);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (10, 4);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 6);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 3);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (8, 9);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (9, 6);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 5);
