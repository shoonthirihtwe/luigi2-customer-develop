truncate table billing_details;
truncate table deposit_details;
truncate table deposit_headers;

INSERT INTO `billing_details` (`id`, `tenant_id`, `billing_period`, `payment_method_code`, `billng_header_no`, `contract_no`, `contract_branch_no`, `premium_due_date`, `premium_sequence_no`) VALUES ('1', '1', '202303', '3', '1', '0000000101', '02', '2022-04-01', '01'),('2', '1', '202304', '3', '2', '0000000101', '02', '2022-05-01', '02'), ('3', '1', '1', '1', '1', '0000000103', '01', '2023-09-01', '01'), ('4', '1', '1', '1', '1', '0000000101', '02', '2023-10-01', '03');

INSERT INTO `deposit_details` (`id`, `tenant_id`,  `contract_no`, `contract_branch_no`, `due_date`, `entry_date`,  `batch_no`, `premium_due_date`) VALUES ('1', '1', '0000000101', '02', '202204', '2022-04-01', 1, '2022-04-01'),('2', '1', '0000000101', '02', '202205', '2022-05-01', 1, '2022-05-01'), ('3', '1', '0000000103', '01', '202309', '2023-09-01', 1, '2023-09-01'), ('4', '1', '0000000101', '02', '202310', '2023-10-01', 1, '2023-10-01');

INSERT INTO `deposit_headers` (`id`, `tenant_id`, `entry_date`, `batch_no`, `deposit_date`) VALUES ('1', '1', '2022-04-01', '1', '2022-04-01'),('2', '1', '2022-05-01', '1', '2022-05-01'), ('3', '1', '2023-09-01', '1', '2023-09-01'), ('4', '1', '2023-10-01', '1', '2023-10-01');
