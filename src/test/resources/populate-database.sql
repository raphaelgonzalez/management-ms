delete from tb_task;
delete from tb_management;

insert into tb_management (id, name)
        values (99,'Raphael');

insert into tb_task (id, description, step, id_management)
        values (99, 'Realizar setup do projeto', 'In Progress', 99);



