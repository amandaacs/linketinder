CREATE TABLE candidato (
  id SERIAL PRIMARY KEY,
  nome varchar(100),
  sobrenome varchar(100),
  dob date,
  email varchar(100) UNIQUE,
  senha varchar(100),
  cpf varchar(14) UNIQUE,
  pais varchar(50),
  cep varchar(20),
  descricao TEXT
);

CREATE TABLE empresa (
  id SERIAL PRIMARY KEY,
  nome varchar(100),
  cnpj varchar(18) UNIQUE,
  email varchar(100) UNIQUE,
  senha varchar(100),
  descricao TEXT,
  pais varchar(50),
  cep varchar(20)
);

CREATE TABLE competencia (
  id SERIAL PRIMARY KEY,
  nome varchar(100) UNIQUE
);

CREATE TABLE vaga (
  id SERIAL PRIMARY KEY,
  nome varchar(150),
  descricao TEXT,
  local varchar (100),
  empresa_id integer REFERENCES empresa(id) ON DELETE CASCADE
);

CREATE TABLE candidato_competencia (
  candidato_id integer REFERENCES candidato(id) ON DELETE CASCADE,
  competencia_id integer REFERENCES competencia(id) ON DELETE CASCADE,
  PRIMARY KEY (candidato_id, competencia_id)
);

CREATE TABLE vaga_competencia (
  vaga_id integer REFERENCES vaga(id) ON DELETE CASCADE,
  competencia_id integer REFERENCES competencia(id) ON DELETE CASCADE,
  PRIMARY KEY (vaga_id, competencia_id)
);

CREATE TABLE like_candidato_vaga (
  candidato_id integer REFERENCES candidato(id) ON DELETE CASCADE,
  vaga_id integer REFERENCES vaga(id) ON DELETE CASCADE,
  PRIMARY KEY (candidato_id, vaga_id)
);

CREATE TABLE like_empresa_candidato (
  empresa_id integer REFERENCES empresa(id) ON DELETE CASCADE,
  candidato_id integer REFERENCES candidato(id) ON DELETE CASCADE,
  PRIMARY KEY (empresa_id, candidato_id)
);


INSERT INTO candidato (id, nome, sobrenome, dob, email, senha, cpf, pais, cep, descricao)
VALUES
  (1, 'Sandubinha', 'Zg', '1998-05-15', 'sandubinha@email.com', 'senha123', '11122233344', 'Brasil', '01001-000', 'Desenvolvedor backend apaixonado por Java e Groovy. Em busca do primeiro match!'),
  (2, 'Carlos', 'Mendes', '1995-10-20', 'carlos.m@email.com', 'devpass456', '22233344455', 'Brasil', '60000-000', 'Especialista em Angular e TypeScript. Foco em interfaces limpas e responsivas.'),
  (3, 'Juliana', 'Costa', '1990-03-08', 'jucosta@email.com', 'sec789', '33344455566', 'Brasil', '20040-002', 'Desenvolvedora Fullstack com forte experiência em C# e .NET. Gosto de resolver problemas complexos.'),
  (4, 'Roberto', 'Almeida', '2001-12-01', 'beto.tech@email.com', 'root321', '44455566677', 'Brasil', '30140-071', 'Entusiasta de dados e backend. Conhecimento sólido em PostgreSQL e Node.js.'),
  (5, 'Elena', 'Rocha', '1997-07-25', 'elena.r@email.com', 'mypass999', '55566677788', 'Brasil', '80010-000', 'Estudante de tecnologia buscando estágio na área de desenvolvimento web. Adoro aprender novas stacks.');
INSERT INTO empresa (id, nome, cnpj, email, senha, descricao, pais, cep)
VALUES
  (1, 'Pastelsoft', '12345678000199', 'recrutamento@pastelsoft.com', 'admin123', 'Especializada em ERPs para restaurantes. Foco em Spring framework e Angular.', 'Brasil', '04538-133'),
  (2, 'Ki-Devs Solutions', '98765432000111', 'vagas@kidevs.com', 'kidevspass', 'Fábrica de software inovadora buscando estagiários e juniores para projetos em TypeScript.', 'Brasil', '60170-001'),
  (3, 'Lynx Data', '55566677000122', 'rh@lynxdata.com', 'lynxsec', 'Empresa de tecnologia focada em soluções financeiras e sistemas robustos utilizando .NET e C#.', 'Brasil', '01310-100'),
  (4, 'InovaWeb Corp', '11222333000144', 'talentos@inovaweb.com', 'inova2026', 'Criamos experiências digitais únicas. Procuramos desenvolvedores apaixonados por código limpo.', 'Brasil', '22290-240'),
  (5, 'GlobalTech BR', '44555666000188', 'careers@globaltech.br', 'global99', 'Consultoria internacional de TI. Projetos desafiadores envolvendo integração de sistemas e APIs.', 'Brasil', '70040-010');

SELECT setval ('candidato_id_seq', (SELECT MAX(id) FROM candidato));
SELECT setval ('empresa_id_seq', (SELECT MAX(id) FROM empresa));
