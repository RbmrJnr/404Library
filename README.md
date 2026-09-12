# 404Library - Sistema Distribuído de Biblioteca


Este projeto foi desenvolvido como parte da disciplina **Sistemas Distribuídos**.  
O objetivo é implementar um sistema de biblioteca com comunicação distribuída, utilizando TCP e Multicast.  
O sistema demonstra conceitos de serialização de objetos, streams personalizados, comunicação cliente-servidor e notificações em grupo.

---

## Tree

´´´
src/
└── br/ufc/qx404library/
├── model/         # Classes POJO (Book, User)
├── io/            # Streams personalizados
├── network/
│    ├── tcp/      # Cliente/Servidor TCP com serialização
│    └── multicast # Cliente/Servidor Multicast com threads
├── service/       # Serviços auxiliares
└── Main.java      # Classe principal
´´´

---

## Como compilar
Dentro da pasta `src`, execute:
```bash
javac $(find br -name "*.java")
