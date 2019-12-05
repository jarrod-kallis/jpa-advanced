# jpa-advanced

<b>@OneToOne</b> & <b>@ManyToOne</b> relationships default to eagerly fetched<br/>
<b>@OneToMany</b> & <b>@ManyToMany</b> relationships default to lazily fetched<br/>
<b>PersistenceContext</b> acts as a storage place during a transaction. It is killed when the transaction finishes and everything has been written to the DB.<br/>
<b>em.flush()</b> writes to the DB, but if something still went wrong afterwards within the same transaction Hibernate would roll everything back.