-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 19/06/2025 às 21:19
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bdtask`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `controle_reset`
--

CREATE TABLE `controle_reset` (
  `id` int(11) NOT NULL,
  `ultima_execucao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `controle_reset`
--

INSERT INTO `controle_reset` (`id`, `ultima_execucao`) VALUES
(1, '2025-06-20');

-- --------------------------------------------------------

--
-- Estrutura para tabela `inventario`
--

CREATE TABLE `inventario` (
  `id_invetario` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `inventario`
--

INSERT INTO `inventario` (`id_invetario`, `usuario_id`, `item_id`) VALUES
(1, 1, 3),
(2, 1, 2),
(3, 1, 4);

-- --------------------------------------------------------

--
-- Estrutura para tabela `itens_loja`
--

CREATE TABLE `itens_loja` (
  `id_loja` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descricao` text DEFAULT NULL,
  `preco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `itens_loja`
--

INSERT INTO `itens_loja` (`id_loja`, `nome`, `descricao`, `preco`) VALUES
(1, 'Espada de Ferro', 'Uma espada simples, mas eficaz em combate corpo a corpo.', 50),
(2, 'Arco de Madeira', 'Arco leve ideal para iniciantes em arquearia.', 40),
(3, 'Cajado Místico', 'Amplifica magias básicas de fogo e gelo.', 120),
(4, 'Poção de Vida', 'Restaura vida.', 30),
(5, 'Poção de Mana', 'Recupera energia mágica.', 35),
(6, 'Escudo de Bronze', 'Aumenta a defesa física em combates.', 60),
(7, 'Elmo de Couro', 'Protege a cabeça contra danos leves.', 25),
(8, 'Armadura de Aço', 'Oferece alta proteção contra ataques físicos.', 200),
(9, 'Anel da Velocidade', 'Aumenta a agilidade do personagem temporariamente.', 150),
(10, 'Bota das Sombras', 'Permite andar silenciosamente por tempo limitado.', 100),
(11, 'Livro de Magias Antigas', 'Contém feitiços raros e poderosos.', 250),
(12, 'Chave do Santuário Perdido', 'Usada para abrir uma porta secreta nas ruínas.', 80),
(13, 'Lanterna Eterna', 'Ilumina áreas escuras infinitamente sem precisar de recarga.', 90),
(14, 'Poção de Invisibilidade', 'Deixa o usuário invisível por 15 segundos.', 180),
(15, 'Martelo de Guerra', 'Arma pesada que causa dano em área.', 160);

-- --------------------------------------------------------

--
-- Estrutura para tabela `personagens`
--

CREATE TABLE `personagens` (
  `id_personagem` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `foco` int(11) DEFAULT 0,
  `agilidade` int(11) DEFAULT 0,
  `inteligencia` int(11) DEFAULT 0,
  `energia` int(11) DEFAULT 0,
  `preco` int(11) DEFAULT 150
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `personagens`
--

INSERT INTO `personagens` (`id_personagem`, `usuario_id`, `foco`, `agilidade`, `inteligencia`, `energia`, `preco`) VALUES
(1, 1, 1, 3, 1, 1, 150);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tarefas`
--

CREATE TABLE `tarefas` (
  `id_tarefas` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `descricao` varchar(150) NOT NULL,
  `recompensa` int(11) DEFAULT 20,
  `dificuldade` enum('FACIL','MEDIO','DIFICIL') NOT NULL,
  `concluido` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tarefas`
--

INSERT INTO `tarefas` (`id_tarefas`, `usuario_id`, `descricao`, `recompensa`, `dificuldade`, `concluido`) VALUES
(1, 1, 'beber agua', 15, 'FACIL', 1),
(2, 1, 'tomar banho', 30, 'MEDIO', 1),
(3, 1, 'Dormir', 50, 'DIFICIL', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(150) NOT NULL,
  `moedas` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nome`, `username`, `email`, `senha`, `moedas`) VALUES
(1, 'a', 'a', 'a', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 100);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `controle_reset`
--
ALTER TABLE `controle_reset`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`id_invetario`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Índices de tabela `itens_loja`
--
ALTER TABLE `itens_loja`
  ADD PRIMARY KEY (`id_loja`);

--
-- Índices de tabela `personagens`
--
ALTER TABLE `personagens`
  ADD PRIMARY KEY (`id_personagem`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Índices de tabela `tarefas`
--
ALTER TABLE `tarefas`
  ADD PRIMARY KEY (`id_tarefas`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `inventario`
--
ALTER TABLE `inventario`
  MODIFY `id_invetario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `itens_loja`
--
ALTER TABLE `itens_loja`
  MODIFY `id_loja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de tabela `personagens`
--
ALTER TABLE `personagens`
  MODIFY `id_personagem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `tarefas`
--
ALTER TABLE `tarefas`
  MODIFY `id_tarefas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE,
  ADD CONSTRAINT `inventario_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `itens_loja` (`id_loja`) ON DELETE CASCADE;

--
-- Restrições para tabelas `personagens`
--
ALTER TABLE `personagens`
  ADD CONSTRAINT `personagens_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

--
-- Restrições para tabelas `tarefas`
--
ALTER TABLE `tarefas`
  ADD CONSTRAINT `tarefas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
