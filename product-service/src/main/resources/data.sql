INSERT INTO TB_CATEGORIAS (CATEGORIA_ID, NOMBRE)
VALUES (1, 'ELECTRODOMESTICOS');
INSERT INTO TB_CATEGORIAS (CATEGORIA_ID, NOMBRE)
VALUES (2, 'DEPORTES');
INSERT INTO TB_CATEGORIAS (CATEGORIA_ID, NOMBRE)
VALUES (3, 'COMIDA');


INSERT INTO TB_PRODUCTOS (NOMBRE, PRECIO, DESCRIPCION, STOCK, ESTADO, FECHACREACION, CATEGORIA_ID)
VALUES ('NEVERA', 1000000.0, 'Nevera HACEB No Frost Congelador Superior 226', 10.00, 'CREATE', '2022-08-25', 1);

INSERT INTO TB_PRODUCTOS (NOMBRE, PRECIO, DESCRIPCION, STOCK, ESTADO, FECHACREACION, CATEGORIA_ID)
VALUES ('BALON', 100000.0, 'BALON AL RIHLA CLUB', 10.00, 'CREATE', '2022-08-25', 2);

INSERT INTO TB_PRODUCTOS (NOMBRE, PRECIO, DESCRIPCION, STOCK, ESTADO, FECHACREACION, CATEGORIA_ID)
VALUES ('PRINGLES', 10000.0, 'PAPAS ORIGINAL PRINGLES 37 gr', 10.00, 'CREATE', '2022-08-25', 3);