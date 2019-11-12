/*
 * Copyright 2019 Web3 Labs LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.quorum;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

import org.web3j.protocol.RequestTester;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.methods.request.PrivateTransaction;

public class RequestTest extends RequestTester {

    private Quorum web3j;

    @Override
    protected void initWeb3Client(HttpService httpService) {
        web3j = Quorum.build(httpService);
    }

    @Test
    public void testSendTransaction() throws Exception {
        web3j.ethSendTransaction(
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendTransaction\",\"params\":[{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testSendRawTransaction() throws Exception {
        String signedTransactionData = "SignedTxData";
        web3j.ethSendRawTransaction(signedTransactionData).send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendRawTransaction\",\"params\":[\"SignedTxData\"],\"id\":1}");
    }

    @Test
    public void testSendRawPrivateTransaction() throws Exception {
        String signedTransactionData = "SignedTxData";
        web3j.ethSendRawPrivateTransaction(
                        signedTransactionData, Arrays.asList("privateFor1", "privateFor2"))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendRawPrivateTransaction\",\"params\":[\"SignedTxData\",{\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testGetPrivateTransaction() throws Exception {
        web3j.quorumGetPrivatePayload("0x").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getQuorumPayload\",\"params\":[\"0x\"],\"id\":1}");
    }

    @Test
    public void testSendTransactionAsync() throws Exception {
        web3j.ethSendTransactionAsync(
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendTransactionAsync\",\"params\":[{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testRaftRole() throws Exception {
        web3j.raftGetRole().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"raft_role\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testRaftLeader() throws Exception {
        web3j.raftGetLeader().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"raft_leader\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testRaftCluster() throws Exception {
        web3j.raftGetCluster().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"raft_cluster\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testRaftRemovePeer() throws Exception {
        web3j.raftRemovePeer(1).send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"raft_removePeer\",\"params\":[1],\"id\":1}");
    }

    @Test
    public void testRaftAddPeer() throws Exception {
        web3j.raftAddPeer("enode").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"raft_addPeer\",\"params\":[\"enode\"],\"id\":1}");
    }

    @Test
    public void testIstanbulGetSnapshot() throws Exception {
        web3j.istanbulGetSnapshot("latest").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"istanbul_getSnapshot\",\"params\":[\"latest\"],\"id\":1}");
    }

    @Test
    public void testIstanbulGetSnapshotAtHash() throws Exception {
        web3j.istanbulGetSnapshotAtHash("blockHash").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"istanbul_getSnapshotAtHash\",\"params\":[\"blockHash\"],\"id\":1}");
    }

    @Test
    public void testIstanbulGetValidators() throws Exception {
        web3j.istanbulGetValidators("latest").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"istanbul_getValidators\",\"params\":[\"latest\"],\"id\":1}");
    }

    @Test
    public void testIstanbulGetValidatorsAtHash() throws Exception {
        web3j.istanbulGetValidatorsAtHash("blockHash").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"istanbul_getValidatorsAtHash\",\"params\":[\"blockHash\"],\"id\":1}");
    }

    @Test
    public void testIstanbulPropose() throws Exception {
        web3j.istanbulPropose("address", true).send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"istanbul_propose\",\"params\":[\"address\",true],\"id\":1}");
    }

    @Test
    public void testIstanbulDiscard() throws Exception {
        web3j.istanbulDiscard("address").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"istanbul_discard\",\"params\":[\"address\"],\"id\":1}");
    }

    @Test
    public void testIstanbulCandidates() throws Exception {
        web3j.istanbulCandidates().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"istanbul_candidates\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testPermissionGetOrgList() throws Exception {
        web3j.quorumPermissionGetOrgList().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_orgList\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testPermissionGetNodeList() throws Exception {
        web3j.quorumPermissionGetNodeList().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_nodeList\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testPermissionGetRoleList() throws Exception {
        web3j.quorumPermissionGetRoleList().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_roleList\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testPermissionGetAccountList() throws Exception {
        web3j.quorumPermissionGetAccountList().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_acctList\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testPermissionAddOrg() throws Exception {
        web3j.quorumPermissionAddOrg(
                        "orgId",
                        "url",
                        "address",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_addOrg\",\"params\":[\"orgId\",\"url\",\"address\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionApproveOrg() throws Exception {
        web3j.quorumPermissionApproveOrg(
                        "orgId",
                        "url",
                        "address",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_approveOrg\",\"params\":[\"orgId\",\"url\",\"address\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionAddSubOrg() throws Exception {
        web3j.quorumPermissionAddSubOrg(
                        "pOrgId",
                        "orgId",
                        "url",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_addSubOrg\",\"params\":[\"pOrgId\",\"orgId\",\"url\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionUpdateOrgStatus() throws Exception {
        web3j.quorumPermissionUpdateOrgStatus(
                        "orgId",
                        1,
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_updateOrgStatus\",\"params\":[\"orgId\",1,{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionApproveOrgStatus() throws Exception {
        web3j.quorumPermissionApproveOrgStatus(
                        "orgId",
                        1,
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_approveOrgStatus\",\"params\":[\"orgId\",1,{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionAddNode() throws Exception {
        web3j.quorumPermissionAddNode(
                        "orgId",
                        "url",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_addNode\",\"params\":[\"orgId\",\"url\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionUpdateNodeStatus() throws Exception {
        web3j.quorumPermissionUpdateNodeStatus(
                        "orgId",
                        "url",
                        1,
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_updateNodeStatus\",\"params\":[\"orgId\",\"url\",1,{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionAssignAdminRole() throws Exception {
        web3j.quorumPermissionAssignAdminRole(
                        "orgId",
                        "address",
                        "roleid",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_assignAdminRole\",\"params\":[\"orgId\",\"address\",\"roleid\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionApproveAdminRole() throws Exception {
        web3j.quorumPermissionApproveAdminRole(
                        "orgId",
                        "address",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_approveAdminRole\",\"params\":[\"orgId\",\"address\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionAddNewRole() throws Exception {
        web3j.quorumPermissionAddNewRole(
                        "orgId",
                        "roleId",
                        1,
                        true,
                        true,
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_addNewRole\",\"params\":[\"orgId\",\"roleId\",1,true,true,{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionRemoveRole() throws Exception {
        web3j.quorumPermissionRemoveRole(
                        "orgId",
                        "roleId",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_removeRole\",\"params\":[\"orgId\",\"roleId\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionAddAccountToOrg() throws Exception {
        web3j.quorumPermissionAddAccountToOrg(
                        "address",
                        "orgId",
                        "roleId",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_addAccountToOrg\",\"params\":[\"address\",\"orgId\",\"roleId\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionChangeAccountRole() throws Exception {
        web3j.quorumPermissionChangeAccountRole(
                        "address",
                        "orgId",
                        "roleId",
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_changeAccountRole\",\"params\":[\"address\",\"orgId\",\"roleId\",{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionUpdateAccountStatus() throws Exception {
        web3j.quorumPermissionUpdateAccountStatus(
                        "orgId",
                        "address",
                        1,
                        new PrivateTransaction(
                                "FROM",
                                BigInteger.ONE,
                                BigInteger.TEN,
                                "TO",
                                BigInteger.TEN,
                                "DATA",
                                "privateFrom",
                                Arrays.asList("privateFor1", "privateFor2")))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_updateAccountStatus\",\"params\":[\"orgId\",\"address\",1,{\"from\":\"FROM\",\"to\":\"TO\",\"gas\":\"0xa\",\"value\":\"0xa\",\"data\":\"0xDATA\",\"nonce\":\"0x1\",\"privateFrom\":\"privateFrom\",\"privateFor\":[\"privateFor1\",\"privateFor2\"]}],\"id\":1}");
    }

    @Test
    public void testPermissionGetOrgDetails() throws Exception {
        web3j.quorumPermissionGetOrgDetails("orgId").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"quorumPermission_getOrgDetails\",\"params\":[\"orgId\"],\"id\":1}");
    }
}
