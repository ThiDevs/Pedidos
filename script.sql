
drop table pedidos;
create table salgados(
cod int primary key auto_increment,
tipo varchar(50),
nome varchar(50),
valor float
);

insert into salgados values(default,'Salgado','Hot Dog Assado',8.6), (default,'Salgado','Hamburguer Assado',2.0),(default,'Salgado','Frango',6.7)
, (default,'Doce','Sorvete',2.0),(default,'Doce','Brigadeiro',4.5)
, (default,'Outros','Cigarro',2.1),(default,'Outros','Isqueiro',5.2);

select nome FROM pedidos ;

create table pedidos(
cod int primary key auto_increment,
nome varchar(50),
setor varchar(50),
pedidos text,
valortotal float,
dat date,
assinatura text

);


