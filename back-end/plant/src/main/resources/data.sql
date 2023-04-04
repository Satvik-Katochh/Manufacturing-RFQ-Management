INSERT INTO Part (part_Id, part_Description,part_Specification,  stock_In_Hand) VALUES (101, 'Gearbox', '6gears',1010);
INSERT INTO Part (part_Id, part_Description,part_Specification,  stock_In_Hand) VALUES (102, 'Differentail', '4x4',1020);
INSERT INTO Part (part_Id, part_Description,part_Specification,  stock_In_Hand) VALUES (103, 'Tyres', 'Ceat',1100);
INSERT INTO Part (part_Id, part_Description,part_Specification,  stock_In_Hand) VALUES (104, 'Engine','1000cc',1100);


INSERT INTO Demand (part_Id, demand_Id,demand_Count, demand_Raised_Date) VALUES (101, 1,1000,'20-04-2022');
INSERT INTO Demand (part_Id, demand_Id,demand_Count, demand_Raised_Date) VALUES (102, 2,1000,'21-05-2022');
INSERT INTO Demand (part_Id, demand_Id,demand_Count, demand_Raised_Date) VALUES (103, 3,1000,'21-05-2022');



INSERT INTO reorderRules (part_Id, min_Quantity, max_Quantity) VALUES (101,50,100);
INSERT INTO reorderRules (part_Id, min_Quantity, max_Quantity) VALUES (102,50,100);
INSERT INTO reorderRules (part_Id, min_Quantity, max_Quantity) VALUES (103,50,100);
INSERT INTO reorderRules (part_Id, min_Quantity, max_Quantity) VALUES (104,50,100);


