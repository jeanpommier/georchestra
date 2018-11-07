/*
 * Copyright (C) 2009-2018 by the geOrchestra PSC
 *
 * This file is part of geOrchestra.
 *
 * geOrchestra is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * geOrchestra is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * geOrchestra.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.georchestra.console.ws.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 *
 * @author Mauricio Pazos
 *
 */
public final class PasswordUtils  {

	public static final int SIZE = 8;

	@Autowired
	private Validation validation;

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

	public void validate(final String password, final String confirmPassword, Errors errors) {

		final String pwd1 = password.trim();
		final String pwd2 = confirmPassword.trim();

		if(!StringUtils.hasLength(pwd1) && validation.isUserFieldRequired("password"))
			errors.rejectValue("password", "password.error.required", "required");

		if(!StringUtils.hasLength(pwd2) && validation.isUserFieldRequired("confirmPassword"))
			errors.rejectValue("confirmPassword", "confirmPassword.error.required", "required");

		if(StringUtils.hasLength(pwd1) && StringUtils.hasLength(pwd2)){
			if(!pwd1.equals(pwd2)){
				errors.rejectValue("confirmPassword", "confirmPassword.error.pwdNotEquals", "These passwords don't match");
			} else {
				if(pwd1.length() < SIZE )
					errors.rejectValue("password", "password.error.sizeError", "The password does have at least 8 characters");
			}
		}
	}

}
