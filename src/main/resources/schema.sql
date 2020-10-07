drop bucket springApp;

create bucket with primary index springApp with { ramquotamb: 128, replicanumber: 0 };

create index type on springApp(_type);