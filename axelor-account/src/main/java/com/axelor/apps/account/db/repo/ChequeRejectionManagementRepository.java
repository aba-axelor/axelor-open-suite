/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2022 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.account.db.repo;

import com.axelor.apps.account.db.ChequeRejection;
import java.math.BigDecimal;

public class ChequeRejectionManagementRepository extends ChequeRejectionRepository {

  @Override
  public ChequeRejection copy(ChequeRejection entity, boolean deep) {
    entity.setName(null);
    entity.setStatusSelect(ChequeRejectionRepository.STATUS_DRAFT);
    entity.setMove(null);
    entity.setAmountRejected(BigDecimal.ZERO);
    entity.setPaymentVoucher(null);
    entity.setPartner(null);
    return super.copy(entity, deep);
  }
}
