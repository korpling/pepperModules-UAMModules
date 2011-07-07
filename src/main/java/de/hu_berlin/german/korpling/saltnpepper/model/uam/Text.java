/**
 * Copyright 2009 Humboldt University of Berlin, INRIA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package de.hu_berlin.german.korpling.saltnpepper.model.uam;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Text#getText <em>Text</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Text#getUamDocument <em>Uam Document</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Text#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getText()
 * @model
 * @generated
 */
public interface Text extends EObject {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getText_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Text#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Uam Document</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument#getTexts <em>Texts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uam Document</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uam Document</em>' container reference.
	 * @see #setUamDocument(UAMDocument)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getText_UamDocument()
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument#getTexts
	 * @model opposite="texts" required="true" transient="false"
	 * @generated
	 */
	UAMDocument getUamDocument();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Text#getUamDocument <em>Uam Document</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uam Document</em>' container reference.
	 * @see #getUamDocument()
	 * @generated
	 */
	void setUamDocument(UAMDocument value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getText_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Text#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Text
