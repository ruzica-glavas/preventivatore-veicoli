Struttura del progetto
**1. Model (Entity)**

Le entità principali del progetto sono:

- Customer: rappresenta un cliente, con campi come nome, cognome, email, telefono e lista di preventivi associati.

- Vehicle: rappresenta un veicolo disponibile, con informazioni come marca, modello, prezzo base e lista di optional.

- VehicleOptional: rappresenta gli optional disponibili per i veicoli (es. cerchi in lega, sensori di parcheggio, ecc.).

- Estimate: rappresenta un preventivo creato per un cliente e un veicolo, con eventuali optional selezionati, prezzo finale calcolato e note.

Ogni entity è collegata tramite relazioni ManyToOne, OneToMany o ManyToMany, e sono presenti annotazioni di validazione per assicurare l’integrità dei dati.

**2. Service (Logica di business)**

La logica applicativa è stata implementata nei service layer:

- CustomerService, VehicleService, VehicleOptionalService, EstimateService

Gestiscono tutte le operazioni CRUD.

Nel caso dei preventivi, il servizio calcola automaticamente:

- Il prezzo finale del veicolo

- L’aggiunta del prezzo degli optional selezionati

- Uno sconto del 3% se si selezionano 3 o più optional

- Uno sconto del 2% se è il primo preventivo del cliente

Il service layer si occupa anche di validare l’esistenza di entità correlate prima di salvare o modificare un record (es. verificare che il veicolo e il cliente esistano prima di creare un preventivo).

**3. Controller (Gestione delle richieste)**

I controller gestiscono le chiamate HTTP e orchestrano il flusso tra Model e View:

- CustomerController, VehicleController, VehicleOptionalController, EstimateController

Ogni controller gestisce:

- Visualizzazione della lista (GET /entity)

- Creazione (GET /entity/create + POST /entity/create)

- Modifica (GET /entity/edit/{id} + POST /entity/edit/{id})

- Cancellazione (POST /entity/delete/{id})

- Visualizzazione dei dettagli di un singolo record (GET /entity/{id})

I controller passano i dati necessari alle View tramite il Model di Spring.

**4. View (Thymeleaf)**

Tutte le pagine sono state realizzate con Thymeleaf, permettendo una gestione dinamica dei dati:

- Template separati per liste, dettagli, form di creazione/modifica

- Gestione degli errori di validazione con messaggi chiari

- Supporto per selezioni multiple (es. optional di un preventivo)

- Pagine interattive con Bootstrap

- Supporto a condizioni dinamiche (es. cambiare titolo da "Crea" a "Modifica" in base alla modalità)

**5. Architettura MVC**

L’intera applicazione segue il pattern Model-View-Controller:

- Model: rappresentazione delle entità (Customer, Vehicle, Optional, Estimate)

- View: pagine HTML dinamiche generate con Thymeleaf

- Controller: riceve le richieste dell’utente, invoca il service appropriato, restituisce la view corretta con i dati del model

Nota: Non sono state create API REST pubbliche, tutte le operazioni avvengono tramite form e routing MVC tradizionale.

**6. Funzionalità principali**

- Gestione completa di clienti, veicoli e optional

- Creazione e modifica di preventivi

- Calcolo automatico del prezzo finale dei preventivi

- Applicazione automatica di sconti

- Validazioni dei dati

- Interfaccia web dinamica e intuitiva

- Possibilità di rimuovere optional da un preventivo

- Gestione dei clienti nuovi e sconti di benvenuto