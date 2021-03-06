-- CLIENTES --

insert into Cliente (nome, email, telemovel) values ('Cynthia Fenne', 'cfenne0@gmail.com', '910285932');
insert into Cliente (nome, email, telemovel) values ('Efrem Shuker', 'eshuker1@gmail.com', '934234915');
insert into Cliente (nome, email, telemovel) values ('Traci Baltrushaitis', 'tbaltrushaitis2@gmail.com', '923843958');
insert into Cliente (nome, email, telemovel) values ('Noemi Passfield', 'npassfield3@hotmail.com', '912602334');
insert into Cliente (nome, email, telemovel) values ('Mill Eisold', 'meisold4@gmail.com', '913945233');
insert into Cliente (nome, email, telemovel) values ('Bliss Lenormand', 'blenormand5@hotmail.com', '969874333');
insert into Cliente (nome, email, telemovel) values ('Valenka Giorgiutti', 'vgiorgiutti6@gmail.com', '912009855');
insert into Cliente (nome, email, telemovel) values ('Benny Madgwich', 'bmadgwich7@gmail.com', '912888473');
insert into Cliente (nome, email, telemovel) values ('Maxie Sawle', 'msawle8@gmail.com', '915867334');

-- Utilizador --

insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Donn Chaperling',    'barca', 'v', '88', '327-200-5486');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Kelwin Kas',         'barca', 'v', 'kkas1@wordpress.org', '516-740-2319');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Rosalinde Roistone', 'barca', 'f', 'rroistone2@senate.gov', '331-726-7035');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Calida Ferrillio',   'barca', 'f', 'cferrillio3@edublogs.org', '581-996-1365');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Aile Glenister',     'barca', 'f', 'aglenister4@theglobeandmail.com', '919-728-3706');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Marcello Schust',    'barca', 'f', '68', '142-638-6615');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Barca',              'barca', 'a', 'shaman', '393-431-6397');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Rosamund Amies',     'barca', 'f', 'ramies7@canalblog.com', '302-309-9053');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Eldridge Neylan',    'barca', 'f', 'eneylan8@hibu.com', '782-967-6449');
insert into Utilizador (nome, password, tipo, email,  telemovel) values ('Bale Catcherside',   'barca', 'f', 'bcatcherside9@huffingtonpost.com', '306-665-1780');

-- Item --

-- CORES -- (1~7)
insert into Item (nome, preco, stock, tipo) values ('Red', 1, 18, 'Cor');
insert into Item (nome, preco, stock, tipo) values ('Blue', 2, 23, 'Cor');
insert into Item (nome, preco, stock, tipo) values ('Black', 3, 21, 'Cor');
insert into Item (nome, preco, stock, tipo) values ('White', 4, 27, 'Cor');
insert into Item (nome, preco, stock, tipo) values ('Yellow', 5, 11, 'Cor');
insert into Item (nome, preco, stock, tipo) values ('Grey', 6, 21, 'Cor');
insert into Item (nome, preco, stock, tipo) values ('Orange', 7, 14, 'Cor');
 
-- MODELO -- (8~11)
insert into Item (nome, preco, stock, tipo) values ('Celica', 8, 22, 'Modelo');
insert into Item (nome, preco, stock, tipo) values ('GTR', 9, 11, 'Modelo');
insert into Item (nome, preco, stock, tipo) values ('NEST', 10, 11, 'Modelo');
insert into Item (nome, preco, stock, tipo) values ('X80-Proto', 11, 11, 'Modelo');

-- EXTERIOR -- (12~20)

-- JANTES -- (12~14)
insert into Item (nome, preco, stock, tipo) values ('Jantes de Alumínio 18"', 12, 11, 'Jantes');
insert into Item (nome, preco, stock, tipo) values ('Jantes Cinzentas Premium 20"', 13, 11, 'Jantes');
insert into Item (nome, preco, stock, tipo) values ('Jantes Pretas Gloss Premium 20"', 14, 11, 'Jantes');

-- PNEUS -- (15~17) 
insert into Item (nome, preco, stock, tipo) values ('Pneus 245/50R18 ALL-SEASON', 15, 11, 'Pneus');
insert into Item (nome, preco, stock, tipo) values ('Pneus 245/40ZR20 SUMMER-SEASON-ONLY', 16, 11, 'Pneus');
insert into Item (nome, preco, stock, tipo) values ('Pneus 245/40R20 ALL-SEASON', 17, 11, 'Pneus');

-- CORPO -- (18~20)

insert into Item (nome, preco, stock, tipo) values ('Para-choque Frontal Básico', 18, 11, 'Corpo');
insert into Item (nome, preco, stock, tipo) values ('Para-choque Frontal e Traseiro Premium', 19, 11, 'Corpo');
insert into Item (nome, preco, stock, tipo) values ('Para-choque Frontal e Traseiro Premium com Asa Traseira', 20, 11, 'Corpo');

-- INTERIOR -- (21~30)

-- VOLANTE -- (24~27)
insert into Item (nome, preco, stock, tipo) values ('Volante Básico', 21, 11, 'Volante');
insert into Item (nome, preco, stock, tipo) values ('Volante Desportivo', 22, 11, 'Volante');
insert into Item (nome, preco, stock, tipo) values ('Volante Confort', 23, 11, 'Volante');
insert into Item (nome, preco, stock, tipo) values ('Volante Nintendo Wii', 24, 11, 'Volante');

-- BANCOS -- (21~23)
insert into Item (nome, preco, stock, tipo) values ('Bancos Básicos', 25, 11, 'Bancos');
insert into Item (nome, preco, stock, tipo) values ('Bancos Premium', 26, 11, 'Bancos');
insert into Item (nome, preco, stock, tipo) values ('Bancos Desportivos', 27, 11, 'Bancos'); 

-- VOLANTE -- (24~27)
insert into Item (nome, preco, stock, tipo) values ('Estofos de Pele', 28, 11, 'Estofos');
insert into Item (nome, preco, stock, tipo) values ('Estofos de Tecido Premium', 29, 11, 'Estofos');
insert into Item (nome, preco, stock, tipo) values ('Estofos de Pele Premium', 30, 11, 'Estofos');

-- OPCIONAIS -- (21~34)

insert into Item (nome, preco, stock, tipo) values ('Bluetooth', 31, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('Pedais Desportivos', 32, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('Sistema de Som Surround', 33, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('LEDs Interiores ', 34, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('Touch Screen', 35, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('Encosto de Cotovelo', 36, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('Vidros Escurecidos', 37, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('Aquecimento de Banco incorporado', 38, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('Turbo', 39, 11, 'Opcional');
insert into Item (nome, preco, stock, tipo) values ('Pack de cartas YuGiOh', 40, 11, 'Opcional');

-- INCOMPATIBILIDADES --

insert into Incompatibilidade (Item_idItem1, Item_idItem2) values (12, 16);
insert into Incompatibilidade (Item_idItem1, Item_idItem2) values (12, 17);
insert into Incompatibilidade (Item_idItem1, Item_idItem2) values (13, 15);
insert into Incompatibilidade (Item_idItem1, Item_idItem2) values (14, 15);
-- DEPENDÊNCIAS --

insert into Dependencia (Item_idItem1, Item_idItem2) values (10, 24);
insert into Dependencia (Item_idItem1, Item_idItem2) values (10, 25);
insert into Dependencia (Item_idItem1, Item_idItem2) values (10, 28);
insert into Dependencia (Item_idItem1, Item_idItem2) values (11, 14);
insert into Dependencia (Item_idItem1, Item_idItem2) values (11, 20);
insert into Dependencia (Item_idItem1, Item_idItem2) values (11, 22);
insert into Dependencia (Item_idItem1, Item_idItem2) values (11, 27);


-- PACOTE --

insert into Pacote (desconto, nome) values (25, 'Desportivo');
insert into Pacote (desconto, nome) values (25, 'Confort');
insert into Pacote (desconto, nome) values (10, 'Premium');

-- Item DE PACOTE --

insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 14);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 17);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 20);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (1, 27);

insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (2, 23);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (2, 26);

insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 19);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 23);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 26);
insert into Pacote_Item (Pacote_idPacote, Item_idItem) values (3, 30);


-- CONFIGURAÇÃO -- 

insert into Configuracao (validade, orcamento, preco, Utilizador_idUtilizador, data, Cliente_idCliente) values ('V', 3500, 150,2, "2018-12-10", 5);
insert into Configuracao (validade, orcamento, preco, Utilizador_idUtilizador, data, Cliente_idCliente) values ('N', 0, 200,1, "2018-12-15", 7);
insert into Configuracao (validade, orcamento, preco, Utilizador_idUtilizador, data, Cliente_idCliente) values ('P', 0,300, 2, "2018-12-20", 2);
insert into Configuracao (validade, orcamento, preco, Utilizador_idUtilizador, data, Cliente_idCliente) values ('V', 4275, 400,1, "2018-12-25", 9);

-- Item DE CONFIGURAÇÃO -- 

insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 1);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 8);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 12);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 15);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 18);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 21);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (1, 24);

insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 3);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 9);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 12);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 15);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 18);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 21);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 26);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (2, 30);

insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 5);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 10);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 13);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 16);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 19);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 22);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 27);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 30);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 34);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (3, 35);

insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 7);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 11);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 14);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 17);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 20);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 25);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 29);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 31);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 33);
insert into Configuracao_Item (Configuracao_idConfiguracao, Item_idItem) values (4, 34);
