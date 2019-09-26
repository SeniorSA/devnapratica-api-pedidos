CREATE TABLE cliente (
	id				BIGSERIAL PRIMARY KEY NOT NULL,
	nome			CHARACTER VARYING(255) NOT NULL,
    email			CHARACTER VARYING(255) NOT NULL,
    data_nascimento	DATE
);
ALTER TABLE cliente ALTER COLUMN id SET DEFAULT nextval('cliente_id_seq'::regclass);


CREATE TABLE produto (
	id				BIGSERIAL PRIMARY KEY NOT NULL,
	descricao		CHARACTER VARYING(255) NOT NULL,
    valor			NUMERIC(12, 2) NOT NULL
);
ALTER TABLE produto ALTER COLUMN id SET DEFAULT nextval('produto_id_seq'::regclass);


CREATE TABLE pedido (
	id				BIGSERIAL PRIMARY KEY NOT NULL,
	id_cliente		BIGSERIAL NOT NULL,
    valor_total		NUMERIC(12, 2) NOT NULL,
    status          CHARACTER VARYING(12) NOT NULL DEFAULT 'PENDENTE',
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);
ALTER TABLE pedido ALTER COLUMN id SET DEFAULT nextval('pedido_id_seq'::regclass);


CREATE TABLE item_pedido (
	id				BIGSERIAL PRIMARY KEY NOT NULL,
	id_pedido		BIGSERIAL NOT NULL,
    id_produto		BIGSERIAL NOT NULL,
    quantidade		NUMERIC(12, 2) NOT NULL DEFAULT 1,
    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (id_pedido) REFERENCES pedido (id),
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (id_produto) REFERENCES produto (id)
);
ALTER TABLE pedido ALTER COLUMN id SET DEFAULT nextval('item_pedido_id_seq'::regclass);